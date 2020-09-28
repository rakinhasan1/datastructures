


#include <iostream>
#include <string>
#include<memory>
template<typename K, typename V>
class HashEntry{
	public:
	K key;
	V value;
	HashEntry(K k,V v){
		this->key=k;
		this->value=v;
	}
	
};

template<typename K, typename V>
class HashMap{
	
	HashEntry<K,V>** arr;
	int capacity;
	int size;
	HashEntry<K,V>* dummy;
	
	void resize(){
		//std::cout<<"resize"<<std::endl;
		capacity*=2;
		HashEntry<K,V> ** temp= new HashEntry<K,V>* [capacity];
		for (int i=0;i<capacity;i++)
			temp[i]=nullptr;
		for(int i=0;i<capacity/2;i++){
			if(arr[i]!=nullptr && arr[i]!=dummy){
				int hashindex= hashCode(arr[i]->key);
				while(temp[hashindex]!=nullptr){
					hashindex++;
					hashindex%capacity;
				}
				temp[hashindex]=arr[i];
			}
			
		}
		
		delete [] arr;
		arr=temp;
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
	
	int hashCode(K k){
		return std::hash<K>()(k)%capacity;
	}
	
	void insert(K key,V value){
		if(size==capacity)
			resize();
		HashEntry<K,V>* temp=new HashEntry<K,V>(key,value);
		int hashindex=hashCode(key);
		while(arr[hashindex]!=nullptr && arr[hashindex]!=dummy && arr[hashindex]->key!=key){
			hashindex++;
			hashindex%=capacity;
		}
		
		if(arr[hashindex]==nullptr|| arr[hashindex]==dummy)
			size++;
		else{
			delete arr[hashindex];
			arr[hashindex]=nullptr;
		}
		arr[hashindex]=temp;
	}
	
	V get(K key){
		//std::cout<<size<<std::endl;
		int hashindex=hashCode(key);
		int counter=0;
		while(arr[hashindex] != nullptr){
			if (counter++>capacity)
				return NULL;
			if(arr[hashindex]!=dummy && arr[hashindex]->key==key)
					return arr[hashindex]->value;
			hashindex++;
			hashindex%=capacity;
			
		}
		return NULL;
	}
	
	
	V deleteNode (K key){
		int hashindex=hashCode(key);
		int counter=0;
		while(arr[hashindex] != nullptr){
			if (counter++>capacity)
				return NULL;
			if(arr[hashindex]!=dummy && arr[hashindex]->key==key){
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
	void display() 
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

class Point{
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
    struct hash<Point>
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