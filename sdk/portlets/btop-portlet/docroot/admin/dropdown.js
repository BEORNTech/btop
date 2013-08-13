
/**
 * Copyright (c) 2007-2013 BEORN Technologies, SARL. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

YUI.add('dropdown', function(Y) {

	Y.namespace('Beorn');
	var B = Y.Beorn;
	
	var Lang = Y.Lang,
		getClassName = Y.ClassNameManager.getClassName,
		DROPDOWN = 'dropdown',
		OPTION = 'option',
	    _classNames = {
			dropdownOption: getClassName(DROPDOWN, OPTION)
	    };

	// DROPDOWN
	var Dropdown = Y.Base.create('dropdown', Y.Widget, [ Y.WidgetPosition, Y.WidgetStack, Y.WidgetPositionAlign ], {
		// Prototype

		initializer : function() {
			this.publish('picked', {
		        emitFacade: true
		    });
		},

		renderUI : function() {
			this.hide();
		},

		bindUI : function() {
			var self = this;
			var contentBox = this.get('contentBox');

			contentBox.delegate('click', function(event) {
				var options = self.get('options');
				var optionNodes = contentBox.all('.'  + _classNames.dropdownOption);
				var option = options[optionNodes.indexOf(event.currentTarget)];
				self.fire('picked', {
					value: option.value,
					context: self.get('context')
				});
				self.close();

			}, '.' + _classNames.dropdownOption);
			
			this.after('optionsChange', function() {
				self._renderOptions();
			});
		},

		syncUI : function() {
			this.set('options', this.get('options'));
		},
		
		open : function(config) {
			var self = this;

	    	this.close();

			this.set('context', config.context);
			this.set('options', config.options);
	    	this.align(config.node, config.align);
	    	this.show();

			setTimeout(function() {
				var contentBox = self.get('contentBox');
				if (self._clickOutsideHandle != null) {
					self._clickOutsideHandle.detach();
					self._clickOutsideHandle = null;
				}
				self._clickOutsideHandle = contentBox.on('clickoutside', function(event) {
					self.close();
				});
			}, 200);
		},
		
		close : function() {
			if (this._clickOutsideHandle != null) {
				this._clickOutsideHandle.detach();
				this._clickOutsideHandle = null;
			}
			this.hide();
		},

		_renderOptions : function() {
			var contentBox = this.get('contentBox');
			contentBox.empty();
			
			var options = this.get('options');
			for (var i = 0; i < options.length; ++i) {
				var option = options[i];
				var optionNode = Y.Node.create('<div class="' + _classNames.dropdownOption + '">' + option.name + '</div>');
				contentBox.append(optionNode);
			}
		}

	}, {
		// Static properties

		ATTRS : {
			options: {
				value: []
			},
			context: {}
		}
	});

	B.Dropdown = Dropdown;

}, '', {
	requires : [ 'widget', 'classnamemanager', 'event-custom', 'widget-position', 'widget-stack', 'widget-position-align', 'event-outside' ]
});
