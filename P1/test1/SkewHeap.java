package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.Comparator;
import java.util.function.UnaryOperator;

public class SkewHeap<E> implements PriorityQueue<E>{
	private Comparator<E> comp;
	private E root;
	private SkewHeap<E> left;
	private SkewHeap<E> right;
	
	public SkewHeap( Comparator<E> comp ){
		this.comp = comp;
	}
	
	public SkewHeap<E> getLeft(){
		if( left == null )
			left = new SkewHeap<>( comp );
		return left;
	}
	
	public SkewHeap<E> getRight(){
		if( right == null )
			right = new SkewHeap<>( comp );
		return right;
	}
	private void setAll( SkewHeap<E> heap ){
		this.root = heap.max();
		this.left = heap.getLeft();
		this.right = heap.getRight();
	}
	private void setRoot( E root ){
		this.root = root;
	}
	
	@Override
	public void insert( E elem ) {
		SkewHeap<E> temp = new SkewHeap<E>( comp );
		temp.setRoot( elem );
		merge( temp );
	}
	
	@Override
	public void merge( PriorityQueue<E> otherQueue ) {
		SkewHeap<E> temp = (SkewHeap<E>)otherQueue;
		if( isEmpty() ){
			setAll( temp );
			return;
		}
		if( temp.isEmpty() )
			return;
		
		if( comp.compare( root, temp.max() ) <= 0 ){
			if(!(right == null)){
				right.merge( temp );
				temp = right;
			}
			right = left;
			left = temp;
		} else if( comp.compare( root, temp.max() ) > 0 ) {
			SkewHeap<E> temp2 = new SkewHeap<E>( comp );
			if( !(temp.getRight() == null) ) {
				merge( temp.getRight() );
				temp2.setAll( this );
				left = temp2;
			}
			root = temp.max();
			right = temp.getLeft();
		}
	}
	
	@Override
	public E deleteMax() {
		E temp = root;
		left.merge( right );
		setAll( left );
		return temp;
	}
	
	@Override
	public E max() {
		return root;
	}
	
	@Override
	public boolean isEmpty() {
		return root == null;
	}
	
	@Override
	public boolean update( E elem, E updatedElem ) {
		if( !isEmpty() ) {
			if( comp.compare( root, elem ) == 0 ) {
				root = updatedElem;
				if( left == null )
					left = new SkewHeap<E>( comp );
				if( right == null )
					right = new SkewHeap<E>( comp );
				if( comp.compare( root, left.max() ) < 0 || comp.compare( root, right.max() ) < 0 ) {
					if( comp.compare( left.max(), right.max() ) < 0 ) {
						SkewHeap<E> temp = left;
						left = null;
						merge( temp );
						return true;
					} else if( comp.compare( left.max(), right.max() ) > 0 ) {
						SkewHeap<E> temp = right;
						right = null;
						merge( temp );
						return true;
					}
				}
			} else {
				return left.update( elem, updatedElem ) || right.update( elem, updatedElem );
			}
		}
		return false;
	}
	
	@Override
	public void map( UnaryOperator<E> f ) {
		left.map( f );
		right.map( f );
		update( root, f.apply( root ) );
	}
}
