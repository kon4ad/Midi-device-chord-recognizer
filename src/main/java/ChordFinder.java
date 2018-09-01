
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import org.jfugue.devices.MusicTransmitterToParserListener;
import org.jfugue.parser.ParserListener;
import org.jfugue.theory.Note;

public class ChordFinder {
    private static List<Chord> chordList;

    public ChordFinder() {
        this.chordList = this.loadFromFileChordList();
    }

    public List<Chord> loadFromFileChordList() {
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        List<Chord> chordList = null;
        try {
            fin = new FileInputStream(System.getProperty("user.dir"));
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


    public void midiListenerWithChordFinder(MidiDevice md){
        try {
            List<String> str = new ArrayList<>();
            MusicTransmitterToParserListener mt = new MusicTransmitterToParserListener(md);
            mt.addParserListener(new ParserListener() {
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

                    try {
                        Runtime.getRuntime().exec("cmd.exe /c clear");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    str.add(note.getToneString());

                    if(str.size() > 2){
                        ChordFinder.chordList.stream().forEach(chord -> {
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
            });
            mt.startListening();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
