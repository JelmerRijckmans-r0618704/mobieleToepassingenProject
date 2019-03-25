package example.com;

public enum CategoryEnum {
    Flowers,
    Pokemon,
    Yugioh{
        public String toString() {
            return "Yu Gi Oh";
        }
    }
}
