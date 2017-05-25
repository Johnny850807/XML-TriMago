import java.util.ArrayList;
import java.util.List;

public class ParallelTasksManager {
	private static ParallelTasksManager instance;
	private List<Thread> threads = new ArrayList<Thread>();

	public static ParallelTasksManager getInstance(){
		if (instance == null)
			instance = new ParallelTasksManager();
		return instance;
	}
	
	public ParallelTasksManager tasks(Task ...tasks) throws Exception {
		threads.clear();
		for (Task t : tasks)
		{
			threads.add(new Thread(){
				@Override
				public void run(){
					try {
						t.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		return this;
	}
	
	public ParallelTasksManager execute() throws Exception{
		for (Thread r : threads)
			r.start();
		return this;
	}
	
	public ParallelTasksManager waiting() throws InterruptedException{
		for (Thread t : threads)
			t.join();
		return this;
	}
	
	public ParallelTasksManager onComplete(Task task) throws Exception{
		task.run();
		return this;
	}
	
	public static interface Task{
		void run() throws Exception;
	}
}
