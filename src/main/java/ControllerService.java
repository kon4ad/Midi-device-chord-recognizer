import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

public class ControllerService {

    private MidiDevice.Info[] getArrayAvailableMidiDevices(){
        return MidiSystem.getMidiDeviceInfo();
    }

    public void printAvailableDevices(){
        MidiDevice.Info[] devicesArray = this.getArrayAvailableMidiDevices();
        if(devicesArray.length == 0){
            System.out.print("No devices connected.");
        }else {
            for(MidiDevice.Info i : devicesArray){
                System.out.println(i);
            }
        }
    }

    public MidiDevice getMidiDevice(int choosenNumber) throws MidiUnavailableException {
        MidiDevice.Info[] deviceArray = this.getArrayAvailableMidiDevices();
            return MidiSystem.getMidiDevice(deviceArray[choosenNumber-1]);
    }
}
