package och08_ex03;

public class DmbCellPhone extends CellPhone {
	int channel;
	
	@Override
	void powerOn() {
		// TODO Auto-generated method stub
		//super.powerOn();
		System.out.println("DmbCell 전원을 켭니다.");
	}
	
	public DmbCellPhone(String model, String color, int channel) {
		this.model = model;
		this.color = color;
		this.channel = channel;
	}
	void turnOnDmb() {
		System.out.println("채널 " + channel + "번 DMB 방송 수신을 시작합니다.");
	}
	void turnOffDmb() {
		System.out.println("DMB 방송 수신을 멈춥니다.");
	}
	void changeChannelDmb(int channel) {
		System.out.println("채널 " + channel + "번으로 바꿉니다.");
		this.channel = channel;
	}
	
}
