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
import java.util.List;

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

/**
	@Override
	public void insert(IntervalADT<T> interval)
					throws IllegalArgumentException {
		root = insertHelp(root, interval);

	}
	
	public IntervalNode<T> insertHelp(IntervalADT<T> root,
			IntervalADT<T> interval ) throws IllegalArgumentException {
		if (root == null){
			return new IntervalNode<T>(interval);
		}
		
		if (root.getInterval().compareTo(interval) == 0){
			throw new IllegalArgumentException();
		}
		
		if (interval.compareTo(root.getInterval())< 0) {
			root.setLeftNode( insertHelp(root.getLeftNode(), interval));
			return root;
		}
		
		else {
			root.setRightNode(insertHelp(root.getRightNode(), interval));
			return root;
		}
	}
*/

	
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
			return null;
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
		// TODO Auto-generated method stub
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
	}

/**
	@Override
	public boolean contains(IntervalADT<T> interval) {
		return contains(interval, root);
	}
	
	private boolean cotains(IntervalADT<T> interval, IntervalNode<T> root){
		if (root.getInterval().compareTo(interval) == 0){
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
*/
	
	@Override
	public void printStats() {
		System.out.println("-----------------------------------------");
		System.out.println("Height: " + getHeight());
		System.out.println("Size: " + getSize());
		System.out.println("-----------------------------------------");
	}

}
