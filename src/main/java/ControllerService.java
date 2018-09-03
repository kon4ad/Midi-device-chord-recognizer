import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

public class ControllerService implements DeviceService {

    public MidiDevice.Info[] getArrayAvailableMidiDevices(){
        return MidiSystem.getMidiDeviceInfo();
    }


    public MidiDevice getMidiDevice(int choosenNumber) throws MidiUnavailableException {
        MidiDevice.Info[] deviceArray = this.getArrayAvailableMidiDevices();
            return MidiSystem.getMidiDevice(deviceArray[choosenNumber-1]);
    }
}
