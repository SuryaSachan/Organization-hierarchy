import java.io.*; 
import java.util.*; 
// Tree node
class Node {
	Node boss = null;
	int Data;
    int level;
    Node manager;
    Vector<Node> emp= new Vector<Node>();
    public Node(int id,int l, Node boss){
	this.Data=id;
	this.level=l;
	this.manager=boss;
   }
public Node(int Data) {
	this.Data = Data;
	}
}
public class OrgHierarchy implements OrgHierarchyInterface{
//root node
Node root;
int id;
Vector<Node> v= new Vector<Node>();

public boolean isEmpty(){
	if(root==null)
		return true;
	else{
		return false;
	}
} 

public int size(){
	return v.size();
}
/*public int size(){
	return orgsize(root);
}

public int orgsize(Node r){
	if(r.emp.size()==0){
		return 0;
	}
	else{
		int n=0;
		while(i < r.emp.size()) {
		n+=orgsize(r.emp.get(i));		
		}

		return r.emp.size()+n;
	}
}
*/
public int level(int id) throws IllegalIDException, EmptyTreeException{
	if(isEmpty()==true){
		throw new EmptyTreeException("First No level tree is empty");}
    Node temp = findNode(id);
    if(temp==null){
	   throw new IllegalIDException("Second ID not present");
   }else{
	   return temp.level;
   }	 
} 

public void hireOwner(int id) throws NotEmptyException{
	if(isEmpty()==false){
		throw new NotEmptyException("Can't hire Owner if tree not Empty");
	}
	else{
		root = new Node(id);
		root.Data=id;
		root.level=1;
		root.manager=null;
		v.add(root);		
	}	
}

public void hireEmployee(int id, int bossid) throws IllegalIDException, EmptyTreeException{
	if(isEmpty()==true) {
		throw new EmptyTreeException("Fourth No tree No employee");}
    Node temp = findNode(bossid);
    if(temp==null){
	   throw new IllegalIDException("Fifth No ID present");
    }
    else{
    	
	    Node node= new Node(id, temp.level+1, temp);
//	    temp.emp.add(node);
		int j = 0;
		while(j < temp.emp.size()) {
			if(temp.emp.get(j).Data < id) {
				j += 1;
			}else {
				break;
			}
		}	
		temp.emp.add(j,node);	
//		System.out.println("start==  " +  temp.emp.get(j).Data);
		
		int i = 0;
		while(i<v.size()) {
			if(v.get(i).Data < id) {
				i += 1;
			}else {
				break;
			}
		}
		v.add(i,node);	
//		System.out.println("__start==  " +  v.get(i).Data);
   }		
} 

private Node findNode(int bossid) {
	int i = 0;
	while(i < v.size()) {
		if(v.get(i).Data == bossid) {
			return v.get(i);
		}else {
			i += 1;
		}
	}
	return null;
}

public void fireEmployee(int id) throws IllegalIDException,EmptyTreeException{
	if(isEmpty()==true) {
		throw new EmptyTreeException("Fourth No tree No employee");}
    Node temp = findNode(id);
    if(temp==null||temp.emp.size()!=0){
	   throw new IllegalIDException("Fifth No ID present");
    }else {
    	
    	Node manage=temp.manager;
		manage.emp.remove(temp);
    	int i = 0;
    	while(i < v.size()) {
    		if(v.get(i).Data == id) {
    			v.remove(i);
    		}else {
    			i += 1;
    		}
    	}    		
    }	
}

public void fireEmployee(int id, int manageid) throws IllegalIDException,EmptyTreeException{
	if(isEmpty()==true) {
		throw new EmptyTreeException("Fourth No tree No employee");}
    Node temp = findNode(id);
    if(temp==null){
	   throw new IllegalIDException("Fifth No ID present");
    }
	else {
    	Node node= temp.manager;
		int i=0;
		while(i<node.emp.size()){
			if(node.emp.get(i).Data==manageid){
				int j=0;
				while(j<temp.emp.size()){
					temp.emp.get(j).manager=node.emp.get(i);
					node.emp.get(i).emp.add(temp.emp.get(j));
					j++;
				}
				//node.emp.get(i).emp=temp.emp;
				break;
			}
			i++;
		}
		if(i>=node.emp.size()){
			throw new IllegalIDException("Fifth No manageID present");
		}

    	node.emp.remove(temp);
    	i = 0;
    	while(i < v.size()) {
    		if(v.get(i).Data == id) {
    			v.remove(i);
    			manageid = id;
    		}else {
    			i += 1;
    		}
    	}
    }
} 

public int boss(int id) throws IllegalIDException,EmptyTreeException{

	if(isEmpty()==true)
	 throw new EmptyTreeException("Sixth No boss if tree is empty");
	Node temp=findNode(id);
	if(temp==null||temp.manager==null){
		throw new IllegalIDException("Seventh No ID present");
	}
	else {
		return temp.manager.Data;
	}
}
public int lowestCommonBoss(int id1, int id2) throws IllegalIDException,EmptyTreeException{
	if(isEmpty()==true){
		throw new EmptyTreeException("Eight No lowestcommonboss tree is empty");
	}
	Node anode=findNode(id1);
	Node bnode=findNode(id2);
	if(anode==null||bnode==null){
		throw new IllegalIDException("Ninth No Id available");
	}
	else{
		if(anode.level>bnode.level){
			while(anode.level!=bnode.level){
				anode=anode.manager;
			}
		}
		else {
			while(anode.level!=bnode.level){
				bnode=bnode.manager;
			}
		}
		while(anode!=bnode){
			anode=anode.manager;
			bnode=bnode.manager;
		}
		return anode.Data;
	}
}


public String toString(int id) throws IllegalIDException, EmptyTreeException{
	String own = "";
	if(isEmpty()) {
		throw new EmptyTreeException("No tree No string");
	}Node temp=findNode(id);
	if(temp==null){
		   throw new IllegalIDException("ID not present can't convert to string");
	}else{
	
		own += temp.Data;
		Vector<Integer> list= new Vector<Integer>();
		Vector<Node> list_node1= new Vector<Node>();
		for(int i=0; i< temp.emp.size(); i++) {	
			list.add(temp.emp.get(i).Data);
			list_node1.add(temp.emp.get(i));
		}		
		own += "," + list.get(0);
		for(int i=1; i< list.size(); i++) {	
			own +=" " + list.get(i);

		}
		
		Vector<Node> list_node2 =  new Vector<Node>();
		while(!list_node1.isEmpty()){
			list.clear();
			
		for(int i=0; i< list_node1.size(); i++) {	
			for(int j=0; j< list_node1.get(i).emp.size(); j++) {
				
				int k = 0;
				while(k < list.size()) {
					if(list.get(k) < list_node1.get(i).emp.get(j).Data) {
						k += 1;
					}else {
						break;
					}
				}	
			list_node2.add(list_node1.get(i).emp.get(j));
			list.add(k, list_node1.get(i).emp.get(j).Data);
			}
		}
		if(!list.isEmpty()) {
		own += "," + list.get(0);}
		for(int i=1; i< list.size(); i++) {	
			
			own +=" " + list.get(i);
			
		}	
		list_node1.clear();
		for(Node n: list_node2) {
			list_node1.add(n);
			
		}
		list_node2.clear();
		
}
}
	return own;
	
}
}









  

