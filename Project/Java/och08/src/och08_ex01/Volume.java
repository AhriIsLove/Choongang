package och08_ex01;

public interface Volume {
	void volumeUp();
	void volumeDown();
}

class Tv implements Volume{

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("Tv Volume Up");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("Tv Volume Down");
	}

	public void play() {
		System.out.println("Tv를 본다.");
	}
}

class Audio implements Volume{

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("Audio Volume Up");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("Audio Volume Down");
	}
	
}

class Speaker implements Volume{

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("Speaker Volume Up");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("Speaker Volume Down");
	}
	
}