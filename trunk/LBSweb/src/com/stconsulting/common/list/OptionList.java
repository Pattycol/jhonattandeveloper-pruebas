/*
 * OptionList.java
 *
 * Created on June 4, 2004, 6:18 PM
 */

package com.stconsulting.common.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.stconsulting.common.bean.*;
/**
 *
 * @author  Administrator
 */
public class OptionList implements List<Option>{
    
    /** Creates a new instance of OptionList */
    public OptionList() {
        super();
    }
    
    public Option getOption(int i){
        return get(i);
    }

	@Override
	public boolean add(Option e){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(int index,Option element){
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(Collection<? extends Option> c){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index,Collection<? extends Option> c){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Option get(int index){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o){
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty(){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Option> iterator(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o){
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<Option> listIterator(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<Option> listIterator(int index){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Option remove(int index){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Option set(int index,Option element){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size(){
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Option> subList(int fromIndex,int toIndex){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T>T[] toArray(T[] a){
		// TODO Auto-generated method stub
		return null;
	}
    
}
