
public interface ChatObservable {
	public void attach(ChatObserver forumObserver);
	public void detach(ChatObserver forumObserver);
	public void inform();

}
