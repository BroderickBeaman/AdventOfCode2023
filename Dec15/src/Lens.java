public class Lens {

    private String label;

    private int focalLength;

    public Lens(String label, int focalLength) {
        this.label = label;
        this.focalLength = focalLength;
    }

    public String getLabel() {
        return label;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Lens lens)) {
            return false;
        }
        return label.equals(lens.getLabel());
    }

    public long hashLabel() {
        long hash = 0;
        for (int i = 0; i < label.length(); i++) {
            hash += label.charAt(i);
            hash = hash * 17;
            hash = hash % 256;
        }
        return hash;
    }
}
