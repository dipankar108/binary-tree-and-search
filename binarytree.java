package binaryprojectleetcode;
import java.util.*;
public class Main {
   
   static class Node{
        
int data;
        Node left;
        Node right;
        Node(int  data){
            this.data=data;
            this.left=null;
            this.right=null;
        }

    }
   static class TreesBuild{
        static int idx=-1;
                public static Node buildTrees(int[] treeData){
     
            idx++;
            if(treeData[idx]==-1){
                return null;
            }
            Node newNode=new Node(treeData[idx]);
            newNode.left=buildTrees(treeData);
            newNode.right=buildTrees(treeData);
            return newNode;
        }
        static void preOrder(Node root){
            if(root==null){
               System.out.print(" -1 ");
                return;
            }
            System.out.print(" "+root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
        static void postOrder(Node root){
            if(root==null) return;
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data);
        }
        
        static void inOrder(Node root){
            if(root==null)return;
            inOrder(root.left);
            System.out.print(root.data);
            inOrder(root.right);
        }
        static void levelBinary(Node root){
            if(root==null)return;
            Queue<Node>q=new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty()){
               Node currentNode=q.remove();
                if(currentNode==null){
                    System.out.println();
                   if(q.isEmpty()){
                        break;
                    }
                    else{
                        q.add(null);
                    }
                }
                else{
                    System.out.print(currentNode.data+" ");
                    if(currentNode.left!=null){
                        q.add(currentNode.left);
                    }
                    if(currentNode.right!=null){
                        q.add(currentNode.right);
                    }
                       
                }
                
            }
        }
   
        static int numberOflevel(Node root){
            if(root==null)return 0;
            int levelLeft=numberOflevel(root.left);
          int levelRight=numberOflevel(root.right);
            int myLevel=Math.max(levelLeft,levelRight)+1;
            return myLevel;
        }
        static int numberOfNode(Node root){
            if(root==null)return 0;
           int left=numberOfNode(root.left);
            int right=numberOfNode(root.right);
            return left+right+1;
        }
          static int sumOfNode(Node root){
            if(root==null)return 0;
            int left=sumOfNode(root.left);
            int right=sumOfNode(root.right);
            return left+right+root.data;
        }
        static int diameter(Node root){
            if(root==null) return 0;
            int leftDiameter=diameter(root.left);
            int rightDiameter=diameter(root.right);
            int rootDiameter=numberOflevel(root.left)+numberOflevel(root.right)+1;
          
            int myDiam=Math.max(Math.max(leftDiameter,rightDiameter),rootDiameter);
            return myDiam;
        }
       static class TreeInfo{
            int diam;
            int height;
            TreeInfo(int diam,int height){
                this.diam=diam;
                this.height=height;
            }
        }
        static TreeInfo diamOptimized(Node root){
            if(root==null){
                return new TreeInfo(0,0);
            }
            TreeInfo left=diamOptimized(root.left);
            TreeInfo right=diamOptimized(root.right);
            int myHeight=Math.max(left.height,right.height)+1;
            int diamLeft=left.diam;
            int diamRight=right.diam;
            int finalDiam=left.height+right.height+1;
            TreeInfo treeInfo=new TreeInfo(finalDiam,myHeight);
           return treeInfo;
        }
        static int kthLevelOfSum(Node root,int k,int level,int sum){
            if(root==null)return 0;
            Queue<Node>nq=new LinkedList<>();
            nq.add(root);
            nq.add(null);
         
            System.out.println(k);
            while(!nq.isEmpty()){
              
                Node currentNode=nq.remove();
                if(currentNode==null){
                   if(nq.isEmpty()){
                         break;
                    }
                    else{
                        level++;
                        nq.add(null);
                    }
                }else{
                   if(level==k){
                        for(int i=0;i<=nq.size();i++){
                            Node mnode=nq.remove();
                            if(mnode==null){
                                sum+=currentNode.data;
                                continue;
                            };
                            sum+=mnode.data;
                            
                        }
                       System.out.print(sum);
                    }else{
                        if(currentNode.left!=null){
                            nq.add(currentNode.left);
                        }
                        if(currentNode.right!=null){
                            nq.add(currentNode.right);
                        }
                           
                    }
                   
                }
            }
            return sum;
        }
    }
    
    public static void main(String[] args) {
        int[]arr = {1,2,4,-1,-1,5,-1,-1,3,-1,6,2,-1,-1,2,-1,-1};
        TreesBuild createTrees=new TreesBuild();
        Node root=createTrees.buildTrees(arr);
       // System.out.print(root.data);
        //createTrees.preOrder(root);
        System.out.println();
       // createTrees.inOrder(root);
          System.out.println();
        //createTrees.postOrder(root);
        createTrees.levelBinary(root);
        System.out.println("Number of nodes : "+createTrees.numberOfNode(root));
        System.out.println("Total sum : "+createTrees.sumOfNode(root));
        System.out.println("Number of level : "+createTrees.numberOflevel(root));
      System.out.println("Number of Diameter : "+createTrees.diameter(root));
        System.out.println("Number of Optimized Diameter : "
        +createTrees.diamOptimized(root).diam);
        System.out.println("Kth level sum : "
        +createTrees.kthLevelOfSum(root,3,0,0));
    }
    
}
