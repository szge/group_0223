import java.util.ArrayList;

private class MemoManager {

    public ArrayList<Memo> store; //holds a list of the Memos

    public MemoManager(){
        this.store = new ArrayList<>();
    }

    public int exists(String content){
        //returns -1 if memo already exists, otherwise returns its id.
        for (int i=0; i<this.store.size(); i++){
            if (this.store.get(i).toString().equals(content)){return this.store.get(i).getId()};
        }
        return -1;
    }

    //For when the memo doesn't exist
    public Memo addMemo(String content){
        //returns Memo after its created
        Memo newMemo = new Memo(content);
        this.store.add(newMemo);
        return newMemo;
    }

    //For when the memo already exists.
    public Memo addMemo(int id){
        //returns already exists in Memo after adding id of the new event to it's list of events.
        for (int i=0; i<this.store.size(); i++){
            if (this.store.get(i).getId() == id){
                this.store.get(i).addEvent(id);
                return this.store.get(i);
            }
        }
    }

    public void removeMemo(String content, int id){
        for (int i=0; i<this.store.size(); i++){
            if (store.get(i).toString().equals(content)) {
                this.store.get(i).removeEvent(id); //Need a Memo method to remove the id of a Memo
            }
        }
        //Do we want to remove a memo that isn't associated with any events anymore?
    }

    public void editMemo(String old, String text){
        for (int i=0; i<this.store.size(); i++){
            if (store.get(i).toString().equals(old)) {
                this.store.get(i).editContent(text); //Need a Memo method to change the content of a Memo
            }
        }
    }




}
