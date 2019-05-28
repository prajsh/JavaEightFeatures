import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String [] args) {
	
		List<Integer> list = getIntegerList();
	
//		System.out.print(list);
//		System.out.println(sumIterator(list));
//		System.out.println("\n"+sumStream(list));
		
//		filterExample();
//		mapExample();
//		sortedExample();
//		flatMapExample();
		
		reduceExample();
		countExample();
		forEachExample();
		matchExample();
		findFirstExample();
		
	}
	
	private static void reduceExample() {

			Stream<Integer> numbers = Stream.of(1,2,3,4,5);
					
			Optional<Integer> intOptional = numbers.reduce((i,j) -> {return i*j;});
			if(intOptional.isPresent()) System.out.println("Multiplication = "+intOptional.get()); //120

	}

	private static void countExample() {
		Stream<Integer> numbers1 = Stream.of(1,2,3,4,5);
		
		System.out.println("Number of elements in stream="+numbers1.count()); //5
	}
	
	private static void forEachExample() {

		Stream<Integer> numbers2 = Stream.of(1,2,3,4,5);
		numbers2.forEach(i -> System.out.print(i+",")); //1,2,3,4,5,

	}
	private static void matchExample() {

		Stream<Integer> numbers3 = Stream.of(1,2,3,4,5);
		System.out.println("Stream contains 4? "+numbers3.anyMatch(i -> i==4));
		//Stream contains 4? true
		
		Stream<Integer> numbers4 = Stream.of(1,2,3,4,5);
		System.out.println("Stream contains all elements less than 10? "+numbers4.allMatch(i -> i<10));
		//Stream contains all elements less than 10? true
		
		Stream<Integer> numbers5 = Stream.of(1,2,3,4,5);
		System.out.println("Stream doesn't contain 10? "+numbers5.noneMatch(i -> i==10));
		//Stream doesn't contain 10? true

	}
	private static void findFirstExample() {

		Stream<String> names4 = Stream.of("Pankaj","Amit","David", "Lisa", "Dravid");
		//Optional<String> firstNameWithD = names4.filter(i -> i.startsWith("D")).findFirst();
		
		Optional<String> firstName = names4.filter(i -> i.equals("Dravid")).findFirst();
		
		if(firstName.isPresent()){
			System.out.println("First Name starting with D="+firstName.get()); //David
		}

	}
	
	
	private static void filterExample() {
		List<Integer> myList = getIntegerList();
		
		Stream<Integer> sequentialStream = myList.stream();
		Stream<Integer> highNums = sequentialStream.filter(p -> p % 2 == 0);
		
		System.out.print( "Even numbers in stream are : ");
		highNums.forEach(p -> System.out.println(p+" "));
	}
	
	private static void mapExample() {
		
		Stream<String> names = Stream.of("aBc", "d", "ef");
		System.out.println(names.map(s -> {return s.toUpperCase();
		}).collect(Collectors.toList()));
		
	}
	
	private static void sortedExample() {
		Stream<String> names2 = Stream.of("aBc", "d", "ef", "123456");
		List<String> reverseSorted = names2.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(reverseSorted); // [ef, d, aBc, 123456]

		Stream<String> names3 = Stream.of("aBc", "d", "ef", "123456");
		List<String> naturalSorted = names3.sorted().collect(Collectors.toList());
		System.out.println(naturalSorted); //[123456, aBc, d, ef]
	}
	
	private static void flatMapExample() {
		Stream <List <String>> namesOriginalList = Stream.of(
				Arrays.asList("Prajjwal"),
				Arrays.asList("Kinjal"),
				Arrays.asList("Prerna"),
				Arrays.asList("Aryan"),
				Arrays.asList("Satyam"));
		Stream<String> flatStream = namesOriginalList.flatMap(strList -> strList.stream());
		System.out.println(flatStream.collect(Collectors.toList()));
	}
	
	private static int sumStream(List <Integer> list) {
		/*
		 * using stream api
		 * internal iteration
		 * 
		 */
		//return list.stream().filter(i -> i > 10).mapToInt(i -> i).reduce(0,(c,e) -> c+e);
		//Stream<Integer> stream = Stream.of(1,2,3,4,5,6);
		Stream<Integer> stream = Stream.of(new Integer[] {1,2,3,4,5,6});
		
		return stream.mapToInt(i -> i).sum();
	}
	
	private static int sumIterator (List<Integer> list) { 
		/*
		 without java-8 stream api:
		 external iteration 
		 sequential in nature, no way to run in parallel
		 lot of code
		 */
		Iterator <Integer> it = list.iterator();
		int sum = 0;
		while (it.hasNext()) {
			int num = it.next();
			sum += num;
		}
		return sum;
	}
	
	private static List<Integer> getIntegerList() {
		List <Integer> list = new ArrayList<>();
		for(int i=1; i<=20; i++)
			list.add(i);
		return list;
	}
}
