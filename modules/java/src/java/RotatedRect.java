package org.opencv;

//javadoc:RotatedRect_
public class RotatedRect {

    public Point center;
    public Size size;
    public double angle;

    public RotatedRect() {
        this.angle=0;
    }

    public RotatedRect(Point c, Size s, double a) {
        this.center = c.clone();
        this.size = s.clone();
        this.angle = a;
    }

    public RotatedRect(double[] vals) {
    	this();
    	if(vals!=null) {
    		center.x    = vals.length>0 ? (int)vals[0] : 0;
    		center.x    = vals.length>1 ? (int)vals[1] : 0;
    		size.width  = vals.length>2 ? (int)vals[2] : 0;
    		size.height = vals.length>3 ? (int)vals[3] : 0;
    		angle       = vals.length>4 ? (int)vals[4] : 0;
    	}
    }

    public void points(Point pt[])
    {
        double _angle = angle*Math.PI/180.0;
        double b = (double)Math.cos(_angle)*0.5f;
        double a = (double)Math.sin(_angle)*0.5f;

        pt[0] = new Point(
                center.x - a*size.height - b*size.width,
                center.y + b*size.height - a*size.width);

        pt[1] = new Point(
                center.x + a*size.height - b*size.width,
                center.y - b*size.height - a*size.width);

        pt[2] = new Point(
                2*center.x - pt[0].x,
                2*center.y - pt[0].y);

        pt[3] = new Point(
                2*center.x - pt[1].x,
                2*center.y - pt[1].y);
    }

    public Rect boundingRect()
    {
        Point pt[] = new Point[4];
        points(pt);
        Rect r=new Rect((int)Math.floor(Math.min(Math.min(Math.min(pt[0].x, pt[1].x), pt[2].x), pt[3].x)),
                (int)Math.floor(Math.min(Math.min(Math.min(pt[0].y, pt[1].y), pt[2].y), pt[3].y)),
                (int)Math.ceil(Math.max(Math.max(Math.max(pt[0].x, pt[1].x), pt[2].x), pt[3].x)),
                (int)Math.ceil(Math.max(Math.max(Math.max(pt[0].y, pt[1].y), pt[2].y), pt[3].y)));
        r.width -= r.x - 1;
        r.height -= r.y - 1;
        return r;
    }


    public RotatedRect clone() {
        return new RotatedRect(center, size, angle);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(center.x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(center.y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(size.width);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(size.height);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(angle);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RotatedRect)) return false;
        RotatedRect it = (RotatedRect) obj;
        return center.equals(it.center) && size.equals(it.size) && angle == it.angle;
    }
}
