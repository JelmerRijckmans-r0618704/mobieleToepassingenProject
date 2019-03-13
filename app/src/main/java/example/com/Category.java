package example.com;

public enum Category {
    Flowers,
    Pokemon,
    Yugioh{
        public String toString() {
            return "Yu Gi Oh";
        }
    }
}
