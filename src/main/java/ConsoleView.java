import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleView {
    private DeviceService controllerService;
    private MidiDeviceChords chordFinder;
    public ConsoleView(){
        this.controllerService = new ControllerService();
        this.chordFinder = new ChordFinder();
    }
    public void startView() {
        System.out.println("*_*_*_*_*_*_Midi Chord Recognizer*_*_*_*_*_*_*");
        System.out.println("from below list choose your midi controller");
        System.out.println("sometimes one midi controller has few outputs");
        System.out.println("you have to choose correct one.");
        this.chordFinder.startMidiListenerWithChordFinder(this.printingAndReturnDeviceService());
        System.out.println("Now you can play! -- Enjoy chord learning!");
    }

    private MidiDevice printingAndReturnDeviceService(){
        int devicesNumber = this.printAvailableDevicesAndReturnDevicesNumber();
        Scanner reader = new Scanner(System.in);
        System.out.println(++devicesNumber + ". " + "RELOAD DEVICES LIST");
        System.out.print("Enter device numer: ");
        int input = reader.nextInt();
        MidiDevice md = null;
        if(input == devicesNumber){
           return this.printingAndReturnDeviceService();
        }else {
            try {
                md = this.controllerService.getMidiDevice(input);
                if(!this.chordFinder.checkMidiDevice(md)){
                    System.out.println("This device cannot be served.");
                    return this.printingAndReturnDeviceService();
                }
            } catch (MidiUnavailableException e) {
                System.out.println("This device throw errors, try with another.");
                return this.printingAndReturnDeviceService();
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Incorrect numer, try again.");
                return this.printingAndReturnDeviceService();
            }
        }
        return md;
    }

    private int printAvailableDevicesAndReturnDevicesNumber(){
        while(true){
            MidiDevice.Info[] dl = this.controllerService.getArrayAvailableMidiDevices();
            if(dl.length == 0){
                System.out.println("Connect device...");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                int cnt = 1;
                for(MidiDevice.Info i : dl){
                    System.out.println(cnt++ + ". "+ i.toString());
                }
                return --cnt;
           }
        }
    }
}
