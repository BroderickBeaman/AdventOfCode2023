public record HailStone(Long x, Long y, Long z, Long dx, Long dy, Long dz, Long a, Long b, Long c) {

    public HailStone(Long x, Long y, Long z, Long dx, Long dy, Long dz) {
        this(
                x,
                y,
                z,
                dx,
                dy,
                dz,
                dy,
                -dx,
                (dy * x) - (dx * y)
        );
    }

    public Point intersection2D(HailStone other) {
        Float a = Float.valueOf(a());
        Float b = Float.valueOf(b());
        Float c = Float.valueOf(c());

        Float otherA = Float.valueOf(other.a);
        Float otherB = Float.valueOf(other.b);
        Float otherC = Float.valueOf(other.c);

        Float x = (c * otherB - otherC * b) / (a * otherB - otherA * b);
        Float y = (otherC * a - c * otherA) / ( a * otherB - otherA * b);
        return new Point(x, y);
    }

    public boolean intersection2DInFuture(Point intersection) {
        return (intersection.x() - x) * dx >= 0 && (intersection.y() - y) * dy >= 0;
    }

}
