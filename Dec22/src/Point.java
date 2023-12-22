public record Point(Integer x, Integer y, Integer z) {


    public Point {
        assert x != null;
        assert y != null;
        assert z != null;
        assert z > 0;
    }

    public Point subtractZ(Integer value) {
        assert z != null;
        return new Point(x(), y(), z() - value);
    }
}
