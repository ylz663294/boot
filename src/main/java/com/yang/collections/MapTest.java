package com.yang.collections;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据订单的经纬度归属所在的商业区域
 *
 * @author lee
 * @date: 2017年2月6日 下午2:12:02
 */
public class MapTest {

    public static void main(String[] args) {
        // 被检测的经纬度点  
        Map orderLocation = new HashMap();
        orderLocation.put("X", "120.145239");
        orderLocation.put("Y", "30.258403");
        // 商业区域（百度多边形区域经纬度集合）  
        String partitionLocation = "30.258403_120.145239,30.256656_120.168882,30.244801_120.151778";
        System.out.println(isInPolygon(orderLocation, partitionLocation));
    }

    /**
     * 判断当前位置是否在多边形区域内
     *
     * @param orderLocation     当前点
     * @param partitionLocation 区域顶点
     * @return
     */
    public static boolean isInPolygon(Map orderLocation, String partitionLocation) {

        double p_x = Double.parseDouble((String) orderLocation.get("X"));
        double p_y = Double.parseDouble((String) orderLocation.get("Y"));
        Point2D.Double point = new Point2D.Double(p_x, p_y);

        List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
        String[] strList = partitionLocation.split(",");

        for (String str : strList) {
            String[] points = str.split("_");
            double polygonPoint_x = Double.parseDouble(points[1]);
            double polygonPoint_y = Double.parseDouble(points[0]);
            Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
            pointList.add(polygonPoint);
        }
        return IsPtInPoly(point, pointList);
    }

    /**
     * 返回一个点是否在一个多边形区域内， 如果点位于多边形的顶点或边上，不算做点在多边形内，返回false
     *
     * @param point
     * @param polygon
     * @return
     */
    public static boolean checkWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
        Point2D.Double first = polygon.get(0);
        p.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double d : polygon) {
            p.lineTo(d.x, d.y);
        }
        p.lineTo(first.x, first.y);
        p.closePath();
        return p.contains(point);
    }

    /**
     * 判断点是否在多边形内，如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
     *
     * @param point 检测点
     * @param pts   多边形的顶点
     * @return 点在多边形内返回true, 否则返回false
     */
    public static boolean IsPtInPoly(Point2D.Double point, List<Point2D.Double> pts) {

        int N = pts.size();
        boolean boundOrVertex = true; //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
        int intersectCount = 0;//cross points count of x
        double precision = 2e-10; //浮点类型计算时候与0比较时候的容差
        Point2D.Double p1, p2;//neighbour bound vertices
        Point2D.Double p = point; //当前点

        p1 = pts.get(0);//left vertex
        for (int i = 1; i <= N; ++i) {//check all rays
            if (p.equals(p1)) {
                return boundOrVertex;//p is an vertex
            }

            p2 = pts.get(i % N);//right vertex
            if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {//ray is outside of our interests
                p1 = p2;
                continue;//next ray left point
            }

            if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {//ray is crossing over by the algorithm (common part of)
                if (p.y <= Math.max(p1.y, p2.y)) {//x is before of ray
                    if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) {//overlies on a horizontal ray
                        return boundOrVertex;
                    }

                    if (p1.y == p2.y) {//ray is vertical
                        if (p1.y == p.y) {//overlies on a vertical ray
                            return boundOrVertex;
                        } else {//before ray
                            ++intersectCount;
                        }
                    } else {//cross point on the left side
                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;//cross point of y
                        if (Math.abs(p.y - xinters) < precision) {//overlies on a ray
                            return boundOrVertex;
                        }

                        if (p.y < xinters) {//before ray
                            ++intersectCount;
                        }
                    }
                }
            } else {//special case when ray is crossing through the vertex
                if (p.x == p2.x && p.y <= p2.y) {//p crossing over p2
                    Point2D.Double p3 = pts.get((i + 1) % N); //next vertex
                    if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {//p.x lies between p1.x & p3.x
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;//next ray left point
        }

        if (intersectCount % 2 == 0) {//偶数在多边形外
            return false;
        } else { //奇数在多边形内
            return true;
        }
    }
}  