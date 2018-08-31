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
        int deviceNumber = this.printAvailableDevicesAndReturnDevicesNumber();

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter device number: ");
        int input = scan.nextInt();
        while(input  == deviceNumber){
            this.printAvailableDevicesAndReturnDevicesNumber();
            System.out.print("Enter device number: ");
            input = scan.nextInt();
        }
        MidiDevice md = null;
        try {
            md = this.controllerService.getMidiDevice(input);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        System.out.print(md);
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
                System.out.println(cnt +". "+ "RELOAD LIST");
                return cnt;
           }
        }
    }
}
