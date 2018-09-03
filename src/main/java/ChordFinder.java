
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import org.jfugue.devices.MusicTransmitterToParserListener;
import com.k8nrd.Chord;

public class ChordFinder implements MidiDeviceChords {
    private List<Chord> chordList;

    public ChordFinder() {
        this.chordList = this.loadFromFileChordList();
    }

    private List<Chord> loadFromFileChordList() {
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        List<Chord> chordList = null;
        try {
            fin = new FileInputStream("C:\\Users\\Konrad\\IdeaProjects\\Chords_recognizer_MIDI\\chords");
            ois = new ObjectInputStream(fin);
            chordList = (List<Chord>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("chord file has not been found in main app folder");
        } catch (IOException e) {
            throw new RuntimeException("Can't load chord list");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Loaded class has not been found");
        }
        return chordList;
    }


    public void startMidiListenerWithChordFinder(MidiDevice md){
        try {
            MusicTransmitterToParserListener mt = new MusicTransmitterToParserListener(md);
            mt.addParserListener(new ParserListenerImpl(this.chordList));
            mt.startListening();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException("Error with midi device");
        }
    }

    public boolean checkMidiDevice(MidiDevice md){
        try {
            new MusicTransmitterToParserListener(md).startListening();

            return true;
        } catch (MidiUnavailableException e) {
            return false;
        }
    }
}
