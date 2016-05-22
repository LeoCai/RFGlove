package dislab.rfidaction.utils;

import java.util.Arrays;
import java.util.List;

import com.alien.enterpriseRFID.tags.Tag;

public class RfidUtils {

	/**
	 * 获取十个手指的rssi数组
	 * 
	 * @param tagList
	 * @param tags
	 * @return
	 */
	public static double[] getTargetTagRssi(Tag[] tagList, List<String> tags) {
		// TODO Auto-generated method stub
		double[] targetTags = new double[tags.size()];
		for (Tag tag : tagList) {
			int index = tags.indexOf(tag.getTagID());
			if (index >= 0) {
				targetTags[index] = tag.getRSSI();
			}
		}
		return targetTags;
	}

	public static double[] getTargetTagRssi(Tag[] tagList, List<String> tags,
			int[] targetIndexes) {
		double[] targetTags = new double[targetIndexes.length];
		for (Tag tag : tagList) {
			int index = tags.indexOf(tag.getTagID());
			if (index >= 0) {
				int index2 = Arrays.binarySearch(targetIndexes, index);
				if (index2 >= 0)
					targetTags[index2] = tag.getRSSI();
			}
		}
		return targetTags;
	}

}
