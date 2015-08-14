package me.gerry.sdkpackage.util;

import java.util.Stack;
/**
 * XML元素存储池，可以将解析时遇到的XML元素压入或弹出该池。
 * @author Gerry
 *
 */
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
