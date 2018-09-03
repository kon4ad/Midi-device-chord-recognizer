import javax.sound.midi.MidiDevice;

public interface MidiDeviceChords {
    boolean checkMidiDevice(MidiDevice md);
    void startMidiListenerWithChordFinder(MidiDevice md);
}
