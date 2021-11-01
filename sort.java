import java.util.Arrays;

public class sort {

	public static void main(String[]args)
	{
		int[]arr={3,11,2,4,22,32,65,10};
		System.out.println("排序前："+Arrays.toString(arr));
		//int[]temp=new int[arr.length];//归并排序需要一个额外空间
		jishuSort(arr/**/);
		System.out.println("排序后："+Arrays.toString(arr));
	}
	public static void bubbleSort(int[]arr)//冒泡排序
	{
		boolean flag=false;
		for(int i=0;i<arr.length-1;i++)
		{
			for(int j=0;j<arr.length-1-i;j++)
			{
				
				if(arr[j]>arr[j+1]){
				    int temp;
				    temp=arr[j];arr[j]=arr[j+1];arr[j+1]=temp;
				    flag=true;
		
				}
				
			}
			System.out.println("排序后："+Arrays.toString(arr));
			if(!flag)break;
			else flag=false;
		}
	}	
	public static void selectSort(int[]arr)//选择排序
	{
		for(int i=0;i<arr.length-1;i++){
			int min=arr[i];
			int minindex=i;
			for(int j=i+1;j<arr.length;j++)
			{
				if(min>arr[j])
				{
					min=arr[j];
					minindex=j;
				}
				
			}
			if(min!=arr[i])
			{
				arr[minindex]=arr[i];
				arr[i]=min;
			}
			System.out.println("排序后："+Arrays.toString(arr));
		}
	}
	public static void insertSort(int[]arr)//插入排序
	{
		for(int i=1;i<arr.length;i++)
		{
			int insertval=arr[i];
			int insertindex=i-1;
			while(insertindex>=0&&insertval<arr[insertindex])
			{
				arr[insertindex+1]=arr[insertindex];
				insertindex--;
			}
			arr[insertindex+1]=insertval;
		}
	}
	public static void shellSort(int[]arr)//希尔排序
	{
		for(int x=1, gap=arr.length/2;gap>0;gap/=2,x++)//gap表示分几组和步长
		{
			for(int i=gap;i<arr.length;i++)
			{
				int j=i;
				int temp=arr[j];//temp记录待插入的元素
				if(temp<arr[i-gap])//保证待插入的元素小于已经排好序的部分的最后一个元素
				{
					while(j-gap>=0&&temp<arr[j-gap])//找待插入元素的位置并且对大于它的元素移动位置
					{
						arr[j]=arr[j-gap];//注意移动gap位
						j=j-gap;
					}
					arr[j]=temp;
				}
			}
			System.out.println("第"+x+"次分组后的排列顺序为："+Arrays.toString(arr));
		}
	}
	public static void radixSort(int[]arr)//基数排序
	{
		//得到数组中的最大数的位数(先得到最大数)
		int max=arr[0];
		for(int i=1;i<arr.length;i++)
		{
			if(arr[i]>max) max=arr[i];
		}
		int maxLength=(max+"").length();//将max加空字符串，调用length方法，得到字符串的有效位数，即数组最大数的位数
		int[][]bucket=new int[10][arr.length];//为防止数据溢出，将每个一维数组（桶）大小定位arr.lenngth
		int[]bucketElementtCounts=new int[10];//用于记录每个桶中的数据个数
	for(int i=1,n=1;i<=maxLength;i++,n*=10)
	{
		for(int j=0;j<arr.length;j++)
		{
			int digitOfElement=j/n%10;//digitOfElement表示当前比较位的值，也就是放入哪个桶
			bucket[digitOfElement][bucketElementtCounts[digitOfElement]]=arr[j];//[bucketElementtCounts[digitOfElement]是digitOfElement这个桶里边的数据个数
			bucketElementtCounts[digitOfElement]++;
		}
		int index=0;
		for(int k=0;k<bucketElementtCounts.length;k++)
		{
			if(bucketElementtCounts[k]!=0)//当第k个桶中有数据时
			{
				for(int l=0;l<bucketElementtCounts[k];l++)//将每个桶中的数据，依次放入arr数组中
				{
					arr[index++]=bucket[k][l];
				}
			}
			bucketElementtCounts[k]=0;
		}
		System.out.println("第"+i+"轮排序后："+Arrays.toString(arr));
	}
	}
	public static void quickSort(int[]arr,int left,int right)//快速排序
	{
		int l=left;//左索引
		int r=right;//右索引
		int pivot=arr[(left+right)/2];//中轴值
		int temp;
		while(l<r)//使比pivot小的数据放在pivot的左边，比pivot大的数据放在pivot的右边
		{
			
			while(arr[l]<pivot)
			{
				l++;
			}
			while(arr[r]>pivot)
			{
				r--;
			}
			if(l>=r)break;//l>=r说明pivot左边的值都小于等于它，右边的值都大于等于它
			temp=arr[l];
			arr[l]=arr[r];
			arr[r]=temp;
			if(arr[l]==pivot)r-=1;//r前移,避免进入死循环
			if(arr[r]==pivot)l+=1;//l后移
		}
		if(l==r)
		{
			l+=1;r-=1;//避免出现栈溢出
		}
		
		if(left<r)quickSort(arr,left,r);System.out.println(Arrays.toString(arr));//向左递归
		if(right>l)quickSort(arr,l,right);//向右递归
		
	}
	public static void mergeSort(int[]arr,int left,int right,int[] temp)//归并排序,分+合
	{
		if(left<right)
		{
			int mid=(left+right)/2;//中间索引
			mergeSort(arr,left,mid,temp);//向左递归进行分解
			mergeSort(arr,mid+1,right,temp);//向右递归进行分解
			//每分解一次就合并一次
			merge(arr,left,mid,right,temp);
		}

	}
	public static void merge(int[]arr,int left,int mid,int right,int[] temp)//left左边有序序列的初始索引，mid中间索引，right右边索引，temp做中转的数
	{
		/*
		合并
		（1）先把左右两边的数据按照规则填充到temp数组，直到左右两边的有序序列，有一边处理完毕为止
		（2）把有剩余的数据依次填充到temp数组
		（3）将temp数组的元素拷贝到arr数组
		*/
		//(1)
		int i=left;//初始化i，左边有序序列的初始索引
		int j=mid+1;//初始化j，右边有序序列的初始索引
		int t=0;//指向temp数组的当前索引
		while(i<=mid&&j<=right)
		{
			//如果左边有序序列的当前元素小于右边有序序列的当前元素，将左边的当前元素拷贝到temp数组，然后i右移，t右移
			if(arr[i]<arr[j])
			{
				temp[t]=arr[i];
				i+=1;
				t+=1;
			}
			//反之，如果左边有序序列的当前元素大于右边有序序列的当前元素，将右边的当前元素拷贝到temp数组，然后j右移，t右移
			else
			{
				temp[t]=arr[j];
				j+=1;
				t+=1;
			}
		}
		//(2)把有剩余数据的一边的数据依次全部填充到temp数组
		while(i<=mid)//左边的有序序列还有剩余的元素
		{
			temp[t]=arr[i];
			t+=1;
			i+=1;
		}
		while(j<=right)//右边的有序序列还有剩余的元素
		{
			temp[t]=arr[j];
			t+=1;
			j+=1;
		}
		//(3)将temp数组中的元素拷贝到arr
		t=0;
		int tempLeft=left;
		while(tempLeft<=right)
		{
			arr[tempLeft]=temp[t];
			t+=1;
			tempLeft+=1;
		}
	}
	public static void jishuSort(int[] arr)
	{
		int min=arr[0];
		for(int i=1;i<arr.length;i++)
		{
			if(arr[i]<min) min=arr[i];
		}
		int max=arr[0];
		for(int i=1;i<arr.length;i++)
		{
			if(arr[i]>max) max=arr[i];
		}
		int[] figure=new int[max-min+1];//figure数组用于计算待排序数组中每个元素出现的次序，数组长度等于代排序数组中最大元素减去最小元素+1
		for(int i=0;i<arr.length;i++)
		{
			figure[arr[i]-min]++;//待排序数组中的元素与figure数组的索引有一定的对应关系
		}
		int begin=0;
		for(int i=0;i<figure.length;i++)
		{
			if(figure[i]!=0)
			{
				for(int j=0;j<figure[i];j++)
				{
					arr[begin++]=i+min;
				}
			}
		}
	}

}






