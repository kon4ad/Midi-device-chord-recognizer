import java.io.Serializable;
import java.util.List;


public class Chord implements Serializable {
    private String fullName;
    private List<String> notesList;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<String> notesList) {
        this.notesList = notesList;
    }

    @Override
    public String toString() {
        return "Chord{" +
                "fullName='" + fullName + '\'' +
                ", notesList=" + notesList +
                '}';
    }
}


