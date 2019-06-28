public class Elem {
    private int number;
    private Elem next;
    Elem(int number){
        this.number = number;
        next = this;
    }
    public int getNumber(){
        return number;
    }
    public int checkLength() {
        int count = 1;
        Elem e = this;
        while (e.next != e){
            e = e.next;
            count++;
        }
        return count;
    }
    public void insertAfter(Elem x){
        if (x == this)
            throw new RuntimeException("The same element");
        Elem z = x.next;
        x.next = this.next;
        Elem k = this;
        while (k.next != k){
            k = k.next;
        }
        if (z != x)
            k.next = z;
    }
    public void deleteAfter(){
        if (this.next != this) {
            Elem z = this.next;
            this.next = z.next;
            z.next = z;
        }
        else throw new RuntimeException("Nothing after" + this);
    }
    public Elem findNumber(int x){
        Elem elem = this;
        if (elem.getNumber() == x){
            return elem;
        }
        while (elem != elem.next){
            elem = elem.next;
            if (elem.getNumber() == x)
                return elem;
        }
        return null;
    }

    public String toString(){
        return  "" + number + "" ;
    }
}






