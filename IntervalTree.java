/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p4
// FILE:             IntervalTree
//
// TEAM:    Team 82 
// Authors: 
// Author1: Matt Marcouiller, mcmarcouille@wisc.edu, 9071489638, 003
// Author2: Zhiheng Wang, zwang759@wisc.edu, 9074796922 ,003
// 
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

public class IntervalTree<T extends Comparable<T>> implements IntervalTreeADT<T> {
	
	private IntervalNode<T> root;
	
	/**
	 * Constructor to create a new IntervalTree
	 * 
	 * @param root 
	 */
	public IntervalTree(IntervalNode<T> root){
		this.root = root;
	}
	
	
	@Override
	/**
	 * Getter for the root
	 * 
	 * @return root
	 */
	public IntervalNode<T> getRoot() {
		return root;
	}


	@Override
	public void insert(IntervalADT<T> interval)
					throws IllegalArgumentException {
		root = insertHelper(root, interval);

	}
	
	private IntervalNode<T> insertHelper(IntervalNode<T> node, 
			IntervalADT<T> interval) 
			throws IllegalArgumentException {
		
		//if interval null
		if(interval == null){
			throw new IllegalArgumentException();
		}
		
		//if root is null 
		if(node == null) {
			return new IntervalNode<T>(interval);
		}
		
		//if root and interval are equal
		if(node.getInterval().compareTo(interval) == 0){
			throw new IllegalArgumentException();
		}
		
		if(node.getInterval().compareTo(interval) > 0){
			//set interval left of root 
			node.setLeftNode(insertHelper(node.getLeftNode(),interval));
			//recheck for MaxEnd and set 
			if(node.getLeftNode().getMaxEnd().compareTo(node.getMaxEnd()) > 0){
				node.setMaxEnd(node.getLeftNode().getMaxEnd());
			}
			return node;
		}
		
		else {
			//set interval right of root 
			node.setRightNode(insertHelper(node.getRightNode(),interval));
			//recheck for MaxEnd and set 
			if(node.getRightNode().getMaxEnd().compareTo(node.getMaxEnd()) > 0){
				node.setMaxEnd(node.getRightNode().getMaxEnd());
			}
			return node;
		}
	}


	
	@Override
	public void delete(IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		root = deleteHelper(root, interval);
	}

	@Override
	public IntervalNode<T> deleteHelper(IntervalNode<T> node,
					IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		
		if (node == null){
			throw new IllegalArgumentException();
		}
		
		if (!contains(interval)){
			throw new IntervalNotFoundException("IntervalNotFound");
		}
		
		
		if (interval.compareTo(node.getInterval()) == 0){
			if (node.getLeftNode() == null && node.getRightNode() == null){
				return null;
			}
			if (node.getLeftNode() == null){
				return node.getRightNode();
			}
			if (node.getRightNode() == null){
				return node.getLeftNode();
			}
			
			//n with two children
			IntervalADT<T> smallVal = root.getSuccessor().getInterval();
			node.setInterval(smallVal);
			node.setMaxEnd(smallVal.getEnd());
			node.setRightNode(deleteHelper(node.getRightNode(), smallVal));
			return node;
		}
		
		//if interval is less than node, go to left tree 
		else if (interval.compareTo(node.getInterval()) < 0){
			node.setLeftNode(deleteHelper(node.getLeftNode(), interval));
			return node;
		}
		
		//if interval is greater than node, go to right tree 
		else{
			node.setLeftNode(deleteHelper(node.getRightNode(), interval));
			return node;
		}
	}

	@Override
	public List<IntervalADT<T>> findOverlapping(
					IntervalADT<T> interval) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<IntervalADT<T>> searchPoint(T point) {
		ArrayList<IntervalADT<T>> list = new ArrayList<IntervalADT<T>>();
		return searchPoint(point, root, list);
	}
	
	private List<IntervalADT<T>> searchPoint(T point, IntervalNode<T> node,
			ArrayList<IntervalADT<T>> list){
		if (node.equals(null)){
			return list;
		}
		
		if (point.compareTo(node.getInterval().getStart()) >= 0 &&
				point.compareTo(node.getInterval().getEnd()) <= 0){
			list.add(node.getInterval());
		}
		
		if(!(node.getLeftNode() == null)){
			searchPoint(point, node.getLeftNode(), list);
		}
		
		if(!(node.getRightNode() == null)){
			searchPoint(point, node.getRightNode(), list);
		}
		
		return list;
	}
	


	@Override
	public int getSize() {
		return getSizeHelper(root);
	}

	private int getSizeHelper(IntervalNode<T> node){
		if (node == null){
			return 0;
		}
		
		//Add one for root
		//Goes down right and left tress and counts number of nodes 
		return 1 + getSizeHelper(node.getLeftNode())
		+ getSizeHelper(node.getRightNode());
	}
	
	
	@Override
	public int getHeight(){
	    return getHeight(root);
	}
	
	private int getHeight(IntervalNode<T> node) {
	    if (node == null) {
	        return 0;
	    }
	    if (node.getLeftNode() == null && node.getRightNode() == null){
	    	return 1;
	    }
	    
	    int lefth = getHeight(node.getLeftNode());
	    int righth = getHeight(node.getRightNode());

	    if (lefth > righth) {
	        return lefth + 1;
	    } else {
	        return righth + 1;
	    }
	}

	@Override
	public boolean contains(IntervalADT<T> interval) {
		return contains(interval, root);	
		}
	
	private boolean contains(IntervalADT<T> interval, IntervalNode<T> root) {
		//if the node's interval is equal to the interval
		if (root.getInterval().compareTo(interval) == 0 ){
			return true;
		}
		
		boolean contain = false; 
		if (root.getLeftNode() != null){
			contain = contains(interval, root.getLeftNode());
		}
		if (!contain && root.getRightNode() != null){
			contain = contains(interval, root.getRightNode());
		}
		return contain;
	}
	


	
	@Override
	public void printStats() {
		System.out.println("-----------------------------------------");
		System.out.println("Height: " + getHeight());
		System.out.println("Size: " + getSize());
		System.out.println("-----------------------------------------");
	}

}
