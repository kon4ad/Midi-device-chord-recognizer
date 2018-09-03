import org.jfugue.parser.ParserListener;
import org.jfugue.theory.Note;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.k8nrd.Chord;
public class ParserListenerImpl implements ParserListener {

    private List<Chord> chordList;
    List<String> str;
    public ParserListenerImpl(List<Chord> chordList){
        this.chordList = chordList;
        this.str = new ArrayList<>();
    }

    @Override
    public void beforeParsingStarts() {
        // System.out.print("cos dziala");
    }

    @Override
    public void afterParsingFinished() {
        //System.out.print("cos dziala");
    }

    @Override
    public void onTrackChanged(byte b) {
        //  System.out.print("cos dziala");
    }

    @Override
    public void onLayerChanged(byte b) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onInstrumentParsed(byte b) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onTempoChanged(int i) {
        //  System.out.print("cos dziala");
    }

    @Override
    public void onKeySignatureParsed(byte b, byte b1) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onTimeSignatureParsed(byte b, byte b1) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onBarLineParsed(long l) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onTrackBeatTimeBookmarked(String s) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onTrackBeatTimeBookmarkRequested(String s) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onTrackBeatTimeRequested(double v) {
        //  System.out.print("cos dziala");
    }

    @Override
    public void onPitchWheelParsed(byte b, byte b1) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onChannelPressureParsed(byte b) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onPolyphonicPressureParsed(byte b, byte b1) {
        //  System.out.print("cos dziala");
    }

    @Override
    public void onSystemExclusiveParsed(byte... bytes) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onControllerEventParsed(byte b, byte b1) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onLyricParsed(String s) {
        //System.out.print("cos dziala");
    }

    @Override
    public void onMarkerParsed(String s) {
        // System.out.print("cos dziala");
    }

    @Override
    public void onFunctionParsed(String s, Object o) {
        //System.out.print("cos dziala");
    }

    @Override
    public void onNotePressed(Note note) {
        /*try {
            Runtime.getRuntime().exec("cmd.exe /c clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        str.add(note.getToneString());
        if(str.size() > 2){
            chordList.stream().forEach(chord -> {
                if(str.containsAll(chord.getNotesList()) && str.size() == chord.getNotesList().size()){

                    System.out.println(chord.getFullName());
                }

            });

        }
    }

    @Override
    public void onNoteReleased(Note note) {
        str.clear();
    }

    @Override
    public void onNoteParsed(Note note) {
    }
    @Override
    public void onChordParsed(org.jfugue.theory.Chord chord) {
        System.out.print(chord.toString());
    }
}
