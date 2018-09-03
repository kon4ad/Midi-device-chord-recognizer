import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;

public interface DeviceService {
    MidiDevice.Info[] getArrayAvailableMidiDevices();
    MidiDevice getMidiDevice(int choosenNumber) throws MidiUnavailableException;
}
