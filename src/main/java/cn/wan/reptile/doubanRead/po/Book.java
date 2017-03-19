package cn.wan.reptile.doubanRead.po;

public class Book {
    private Integer id;

    private String bookname;

    private String author;

    private String publishyear;

    private String pages;

    private String price;

    private String score;

    private String content;

    private String authorinfo;

    private Integer booktag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPublishyear() {
        return publishyear;
    }

    public void setPublishyear(String publishyear) {
        this.publishyear = publishyear == null ? null : publishyear.trim();
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages == null ? null : pages.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAuthorinfo() {
        return authorinfo;
    }

    public void setAuthorinfo(String authorinfo) {
        this.authorinfo = authorinfo == null ? null : authorinfo.trim();
    }

    public Integer getBooktag() {
        return booktag;
    }

    public void setBooktag(Integer booktag) {

        this.booktag = booktag;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", author='" + author + '\'' +
                ", publishyear='" + publishyear + '\'' +
                ", pages='" + pages + '\'' +
                ", price='" + price + '\'' +
                ", score='" + score + '\'' +
                ", content='" + content + '\'' +
                ", authorinfo='" + authorinfo + '\'' +
                ", booktag=" + booktag +
                '}';
    }
}