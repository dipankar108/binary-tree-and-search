
package binaryprojectleetcode;
import java.util.*;
public class binarysearch {
    //Creating Node for BST
  static class Node{
        Node left;
        Node right;
        int data;
       Node(int data){
             this.data=data;
        }
    }
    
    static class BinarySearch{
        //Insert node into BST
        static Node insert(Node root,int val){
            if(root==null){
                return new Node(val);
            }
            if(root.data>val){
                root.left=insert(root.left,val);
            }else if(root.data<val){
                root.right=insert(root.right,val);
            }
            return root;
        }
        //Getting inorder BST
    static void inorder(Node root){
        if(root==null)return ;
        inorder(root.left);
        System.out.print(root.data);
        inorder(root.right);    
        }
        //Searching value from BST
        static boolean search(Node root,int val){
            if(root==null)return false;
            if(root.data>val){
               return search(root.left,val);
            }
            if(root.data<val){
                return search(root.right,val);
            }
            if(root.data==val){
                return true;
            }else return false;
        }
        //Deleting value from BST
        static Node delete(Node root,int val){
            if(root==null)return null;
            if(root.data>val){
                root.left=delete(root.left,val);
               
            }
            if(root.data<val){
                root.right=delete(root.right,val);
            }
            if(root.data==val){
                //case 1
                if(root.left==null && root.right==null){
                    return null;
                }
                //case 2
                else if(root.left==null){
                    return root.right;
                }
                else if(root.right==null){
                    return root.left;
                }
                //case3
                
                else{
                    int ds=inrodersuccessor(root.right).data;
                    root.data=ds;
                }
                
            }
            return root;
        }
        //Getting inorder successor from BST
        static Node inrodersuccessor(Node root){
            while(root.left != null){
                root=root.left;
            }
            return root;
        }
        //Print in range from BST
        static void printInRange(Node root,int x,int y){
            if(root==null)return;
            if(root.data>=x && root.data<=y){
                printInRange(root.left,x,y);
                System.out.print(root.data);
                printInRange(root.right,x,y);
            }else if(root.data>y){
                printInRange(root.left,x,y);
            }else{
                printInRange(root.right,x,y);
            }
            return;
        }
    //Printing Path
    static void printPath(Node root,ArrayList<Integer>path){
      if(root==null) return;
      path.add(root.data);
      if(root.left==null && root.right==null){
        printNodes(path);
      }else{
        printPath(root.left,path);
        printPath(root.right,path);
      }
      path.remove(path.size()-1);
      return;
    }
    //print function for printing path
    static void printNodes(ArrayList<Integer>path){
      for(int i:path){
        System.out.print(i+"->");
      }
      System.out.println();
    }
    }
    public static void main(String[] args) {
        int[] values={5,1,3,4,2,7};
        Node root=null;
        BinarySearch bs=new BinarySearch();
        for(int v : values){
            root=bs.insert(root,v);
        }
        bs.inorder(root);
        System.out.println();
        if(bs.search(root,9)){
            System.out.print("Number found");
        }else{
            System.out.print("Number not found");
        }
        System.out.println();
        root=bs.delete(root,1);
        bs.inorder(root);
        System.out.println();
        bs.printInRange(root,2,4);
    System.out.println();
    bs.printPath(root,new ArrayList());
    }
    
}
