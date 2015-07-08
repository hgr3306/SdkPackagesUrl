package me.gerry.sdkpackage.util;

import java.util.Stack;

public class XmlElementPool {

    private Stack<String> mStack;

    public XmlElementPool() {
        this.mStack = new Stack<String>();
    }

    public void addElement(String element) {
        this.mStack.push(element);
    }

    public void removeElement() {
        this.mStack.pop();
    }

    public String previousElement() {
        return this.mStack.peek();
    }
}
