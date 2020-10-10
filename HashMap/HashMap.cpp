//Program to test linear probing hashing algorithm


#include <iostream>
#include <string>
#include<memory>
template<typename K, typename V>
class HashEntry{		//hash entry classs
	public:
	K key;
	V value;
	HashEntry(K k,V v){
		this->key=k;
		this->value=v;
	}
	
};

template<typename K, typename V>
class HashMap{		//hash map class
	
	HashEntry<K,V>** arr;	//have an array of pointers to hash entries
	int capacity;
	int size;
	HashEntry<K,V>* dummy;  //dummy pointer, use this when an entry needs to be deleted
	
	void resize(){		//resize when capacity is reached
		//std::cout<<"resize"<<std::endl;
		capacity*=2;
		HashEntry<K,V> ** temp= new HashEntry<K,V>* [capacity];
		for (int i=0;i<capacity;i++)
			temp[i]=nullptr;
		for(int i=0;i<capacity/2;i++){	//go through the old hash table
			if(arr[i]!=nullptr && arr[i]!=dummy){	//checks if it is a valid entry
				int hashindex= hashCode(arr[i]->key);  //recalculate the hash, then put it back into the table
				while(temp[hashindex]!=nullptr){	//from the hash, find the next empty spot in the table
					hashindex++;
					hashindex%capacity;
				}
				temp[hashindex]=arr[i];		
			}
			
		}
		
		delete [] arr;		//delete the old pointer
		arr=temp;		//reassign the value of arr
	}
	
	public:
	HashMap(){
		capacity=5;		
		size=0;
		dummy=new HashEntry<K,V>(NULL,NULL);
		arr=new HashEntry<K,V>*[capacity];
		for(int i=0;i<capacity;i++){
			arr[i]=nullptr;
		}
	}
	virtual ~HashMap();
	
	int hashCode(K k){		//calculate hash code
		return std::hash<K>()(k)%capacity;
	}
	
	void insert(K key,V value){
		if(size==capacity)		//resize when size goes up to the capacity
			resize();
		HashEntry<K,V>* temp=new HashEntry<K,V>(key,value);
		int hashindex=hashCode(key);		//calculate hashcode
		while(arr[hashindex]!=nullptr && arr[hashindex]!=dummy && arr[hashindex]->key!=key){ //find next nonempty entry in the table or stop if the key is already in the table
			hashindex++;
			hashindex%=capacity;
		}
		
		if(arr[hashindex]==nullptr|| arr[hashindex]==dummy)		//increment size if added to a empty spot
			size++;
		else{
			delete arr[hashindex];			
			arr[hashindex]=nullptr;
		}
		arr[hashindex]=temp;
	}
	
	V get(K key){		//get method given a key
		//std::cout<<size<<std::endl;
		int hashindex=hashCode(key); //calculate the hashcode
		int counter=0; //this variable is to make sure the loop stops when the whole array has been searched
		while(arr[hashindex] != nullptr){
			if (counter++>capacity)	//increment counter each time
				return NULL;
			if(arr[hashindex]!=dummy && arr[hashindex]->key==key) //make sure the key is at the index
					return arr[hashindex]->value;
			hashindex++;
			hashindex%=capacity;
			
		}
		return NULL;
	}
	
	
	V deleteNode (K key){		//this will put dummy node in the spot that is to be deleted, it is a lazy deletion
		int hashindex=hashCode(key); //first find if the key is in the hash table
		int counter=0;
		while(arr[hashindex] != nullptr){
			if (counter++>capacity)
				return NULL;
			if(arr[hashindex]!=dummy && arr[hashindex]->key==key){ //if it is in the hash table, delete the pointer and replace it with dummy node
				V temp=arr[hashindex]->value;
				HashEntry<K,V>* h=arr[hashindex];
				arr[hashindex]=dummy;
				size--;
				delete h;
				return temp;
			}
			hashindex++;
			hashindex%=capacity;
			
		}
		
		
		return NULL;
	}
	
	
	void deleteEntries(){		
		//std::cout<<"delete";
		//delete [] arr;
		delete dummy;
		dummy=nullptr;
		for (int i=0;i<capacity;i++){
			if (arr[i]!=nullptr){
				delete arr[i];
				arr[i]=nullptr;
			}
		}
		delete [] arr;
		
	}
	void display() 			//method to display all key-value pairs
    { 
        for(int i=0 ; i<capacity ; i++) 
        { 
            if(arr[i] != nullptr && arr[i]!= dummy) 
                std::cout << "key = " << arr[i]->key  
                     <<"  value = "<< arr[i]->value << std::endl; 
        } 
    } 
};


template <typename k,typename v>
HashMap<k,v>::~HashMap(){
	//std::cout<<"Destructor";
	deleteEntries();
}

class Point{		//random custom class I made to test with the HashMap
	public:
		int x;
		int y;
		Point(int x=0,int y=0):x(x),y(y){}
		bool operator ==(const Point &other) const{
			return (x==other.x) && (y==other.y);
		}
};
namespace std
{
    template <>
    struct hash<Point>		//hash method for point class
    {
        size_t operator()(const Point& k) const
        {
            return (std::hash<int>()(k.x)+std::hash<int>()(k.y));
        }
    };
}
int main(){
	//::string x="abc";
	//std::hash<std::string> h;
	//std::cout<<std::hash<std::string>()(x);
	/*int *pointer =(int*)malloc(sizeof(int)*10);
	pointer[4]=40;
	delete[] pointer;
	Point p=Point();
	Point p1=Point();
	std::cout<<(p==p1)<<std::endl;
	std::cout<<std::hash<Point>()(p);*/
	//HashMap <int,char>* h= new HashMap<int,char>();
	/*std::unique_ptr<HashMap<char,int>> h(new HashMap<char,int>()); 
	h->insert('a',1);
	h->insert('b',2);
	h->insert('c',3);
	h->insert('d',4);
	h->insert('e',5);
	h->insert('f',6);
	h->deleteNode('g');
	h->insert('b',7);*/
	std::unique_ptr<HashMap<int,char>> h(new HashMap<int,char>());
	h->insert(1,'a');
	h->insert(2,'b');
	h->insert(3,'c');
	h->insert(4,'d');
	h->insert(5,'e');
	h->insert(6,'f');
	h->insert(10,'y');
	h->insert(12,'l');
	h->deleteNode(92);
	h->deleteNode(6);
	h->insert(2,'g');
	h->display();
	std::cout<<h->get(3)<<std::endl;
	std::cout<<h->get(5)<<std::endl;
	//std::cout<< h->get('b')<<std::endl;
	return 0;
}
