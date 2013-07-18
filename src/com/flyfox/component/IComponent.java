package com.flyfox.component;

import com.jfinal.plugin.IPlugin;

public interface IComponent extends IPlugin{
	boolean start();
	boolean stop();
}
