@Entity
public class Comment {
    @Entity
    public class Comment {
        @Id
        @Type (type = "uuid-char")
        public UUID id;
    
        @Column (nullable = false)
        public String text;
    
        @Column (nullable = false)
        public Date date;
    
        @Column (nullable = false)
        public int rating;
    }
    
}

