package nl.nkiavl.rharkes;

import org.scijava.app.StatusService;
import org.scijava.command.Command;
import org.scijava.log.LogService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;

import net.imagej.Dataset;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.Type;

/**
 * Subtracts the temporal median
 * @param <T>
 */
@Plugin(type = Command.class, headless = true,
menuPath = "Plugins>Process>CummulativeMaximum")
public class CummulativeMaximum implements Command{

	@Parameter
	private LogService log;

	@Parameter
	private StatusService statusService;

	@Parameter
	private UIService uiService;

	@Parameter
	private Dataset inputData;

	public static void main(final String... args) throws Exception {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		final long[] dims = new long[inputData.numDimensions()];
		inputData.dimensions(dims);
		@SuppressWarnings("rawtypes")
		RandomAccessibleInterval randomAccessibleInterval = inputData;
		computeCM(randomAccessibleInterval.randomAccess(),dims);
	}

	public < T extends Comparable< T > & Type< T > > void computeCM(RandomAccess< T > input,long[] dims)
	{
		int[] pos = { 0,0,0};
		for (int x=0;x<dims[0];x++) {
			pos[0]=x;
			for (int y=0;y<dims[1];y++) {
				pos[1]=y;
				input.setPosition(pos);
				T max = input.get().copy();
				for ( int fr =0;fr<dims[2];fr++)
				{
					// move forward
					input.fwd(2);
					if (max.compareTo(input.get())<0) {
						max = input.get().copy();
					} else {
						input.get().set(max.copy());
					}
				}
			}
		}

	}
}
