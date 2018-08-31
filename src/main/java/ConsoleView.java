import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleView {
    private ControllerService controllerService;
    public ConsoleView(){
        this.controllerService = new ControllerService();
    }
    public void startView() {
        System.out.println("*_*_*_*_*_*_Midi Chord Recognizer*_*_*_*_*_*_*");
        System.out.println("from below list choose your midi controller");
        System.out.println("sometimes one midi controller has few outputs");
        System.out.println("you have to choose correct one.");
        this.printAvailableDevicesAndReturnDevicesNumber();
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
