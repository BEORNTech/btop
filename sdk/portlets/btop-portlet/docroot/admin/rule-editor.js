
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

YUI.add('rule-editor', function(Y) {

	Y.namespace('Beorn');
	var B = Y.Beorn;
	
	var Lang = Y.Lang,
		getClassName = Y.ClassNameManager.getClassName,
		RULE_EDITOR = 'ruleeditor',
		CONDITION_CONTAINER = 'conditioncontainer',
		FIELD_CONTAINER = 'fieldcontainer',
		CONDITION_BODY = 'conditionbody',
	    _classNames = {
			ruleEditor: getClassName(RULE_EDITOR),
	        ruleEditorConditionContainer: getClassName(RULE_EDITOR, CONDITION_CONTAINER),
	        conditionFieldContainer: getClassName(RULE_EDITOR, FIELD_CONTAINER),
	        conditionBody: getClassName(RULE_EDITOR, CONDITION_BODY)
	    };
	
	// EDITOR
	var RuleEditor = Y.Base.create('ruleEditor', Y.Widget, [ Y.WidgetParent ], {
		// Prototype

		renderUI : function() {
			var contentBox = this.get('contentBox');
			this._renderConditionContainer(contentBox);
			this._renderConditionTypesDropdown(contentBox);
		},

		bindUI : function() {
			this._bindEditorUI();
		},

		syncUI : function() {
		},

		createCondition : function(config) {
			return new B.RuleEditor.Condition(config);
		},
		
		createGroupCondition : function(config) {
			return new B.RuleEditor.GroupCondition(config);
		},
		
		createDefaultCondition : function() {
			return this.createGroupCondition({
				logicOperator: 'all'
			});
		},

	    _renderConditionContainer: function(contentBox) {
	        var node = this.get('conditionContainerNode');
	        if (!node.inDoc()) {
	            contentBox.append(node);
	        }
	    },

		_renderConditionTypesDropdown: function(contentBox) {
			var dropdown = new B.Dropdown({
			});
	    	dropdown.render(contentBox);
	    	dropdown.addTarget(this);

	    	this._conditionTypesDropdown = dropdown;
		},

		_bindEditorUI : function() {
			this.on('*:addCondition', Y.bind(this._onAddCondition, this));
			this.on('*:removeCondition', Y.bind(this._onRemoveCondition, this));
			this.on('*:picked', Y.bind(this._onPickedConditionType, this));
		},

	    _onAddCondition: function(event) {
	    	var conditionBuilders = this.get('conditionBuilders');
	    	
			var dropdownOptions = [];
	    	for (var conditionBuilderKey in conditionBuilders) {
	    		var conditionsBuilder = conditionBuilders[conditionBuilderKey];

	    		dropdownOptions.push({
	    			name: conditionsBuilder.name,
	    			value: conditionBuilderKey
	    		});
	    	}

	    	this._conditionTypesDropdown.open({
	    		node: event.buttonNode, 
	    		align: [Y.WidgetPositionAlign.TC, Y.WidgetPositionAlign.BC], 
	    		options: dropdownOptions, 
	    		context: {
		    		toCondition: event.toCondition
		    	}
	    	});
	    },

	    _onRemoveCondition: function(event) {
	    	var condition = event.condition;
	    	var isGroup = condition.get('conditionContainerNode') != null;
	    	var isRoot = condition.get('parent') == condition.get('root');
	    	if (isGroup && !isRoot) {
	    		var conditionParent = condition.get('parent');
	    		while (!condition.isEmpty())
	    			conditionParent.add(condition.item(0));
	    	}
	    	condition.remove();
	    	if (this.isEmpty())
		    	this.add(this.createDefaultCondition());
	    },
	    
	    _onPickedConditionType: function(event) {
	    	var conditionBuilders = this.get('conditionBuilders');
	    	var conditionBuilder = conditionBuilders[event.value];

	    	var toCondition = event.context.toCondition;
	    	if (toCondition.get('conditionContainerNode') == null) {
	    		var toConditionParent = toCondition.get('parent');
	    		var groupCondition = this.createDefaultCondition();
	    		toConditionParent.add(groupCondition);
	    		groupCondition.add(toCondition);
	    		groupCondition.add(conditionBuilder.build());

	    	} else {
		    	toCondition.add(conditionBuilder.build());
	    	}
	    },
	    
		_defConditionContainerNodeValueFn : function() {
			return Y.Node.create(B.RuleEditor.CONDITION_CONTAINER_TEMPLATE);
		}

	}, {
		// Static properties

	    CONDITION_CONTAINER_TEMPLATE: '<div class="' + _classNames.ruleEditorConditionContainer + '"></div>',
	    
		ATTRS : {
			translations: {},
			conditionBuilders : {
				value : {}
			},
			conditionContainerNode: {
	            setter: function(node) {
	                node = Y.one(node);
	                if (node) {
	                    node.addClass(_classNames.ruleEditorConditionContainer);
	                }
	                return node;
	            },
	            valueFn: '_defConditionContainerNodeValueFn'
	        }
		}
	});

	// BASE CONDITION
	var BaseCondition = Y.Base.create('ruleEditorBaseCondition', Y.Widget, [ Y.WidgetParent, Y.WidgetChild ], {
		// Prototype

		initializer : function() {
			this.publish('addCondition', {
		        emitFacade: true
		    });
			this.publish('removeCondition', {
		        emitFacade: true
		    });
		},

		renderUI : function() {
			this._appendToParent();

			var contentBox = this.get('contentBox');
			this._renderButtons(contentBox);
			this._renderConditionBodyNode(contentBox);
		},

		bindUI : function() {
			this._bindButtons();
		},

		_appendToParent : function() {
			var parent = this.get('parent');
			if (parent == null)
				return;

			var boundingBox = this.get('boundingBox');
			var container = parent.get('conditionContainerNode');
			container.append(boundingBox);
		},
		
		_renderButtons : function(contentBox) {
			var buttonContainer = Y.Node.create('<div class="button-container"></div>');
			this._addButton = Y.Node.create('<div class="add-button">Add</div>');
			this._removeButton = Y.Node.create('<div class="remove-button">Remove</div>');
			buttonContainer.append(this._addButton);
			buttonContainer.append(this._removeButton);
			contentBox.append(buttonContainer);
		},
		
		_bindButtons : function() {
			this._addButton.on('click', Y.bind(this._onAddButtonClicked, this));
			this._removeButton.on('click', Y.bind(this._onRemoveButtonClicked, this));
		},

		_onAddButtonClicked: function(event) {
			this.fire('addCondition', {
				toCondition: this,
				buttonNode: this._addButton
			});
		},
		
		_onRemoveButtonClicked: function(event) {
			this.fire('removeCondition', {
				condition: this
			});
		},

	    _renderConditionBodyNode: function(contentBox) {
			var inner = Y.Node.create('<div class="inner"></div>');
			
			var conditionBodyNode = Y.Node.create('<div class="' + _classNames.conditionBody + '"></div>');
			conditionBodyNode.append(inner);

			contentBox.append(conditionBodyNode);
			
			this.set('conditionBodyNode', inner);
			
			return inner;
	    }

	}, {
		// Static properties
	    
		CONDITION_CONTAINER_TEMPLATE: '<div class="' + _classNames.conditionConditionContainer + '"></div>',
	    
		ATTRS : {
			conditionBodyNode: {}
		}
	});

	// CONDITION
	var Condition = Y.Base.create('ruleEditorCondition', BaseCondition, [ Y.WidgetParent, Y.WidgetChild ], {
		// Prototype

		renderUI : function() {
			this._appendToParent();

			var contentBox = this.get('contentBox');
			this._renderButtons(contentBox);
			
	        var conditionBodyNode = this._renderConditionBodyNode(contentBox);
	        
			this._renderNegationToggle(conditionBodyNode);
			this._renderDescription(conditionBodyNode);
			this._renderFieldContainer(conditionBodyNode);
		},

		bindUI : function() {
			this._bindButtons();
			this._bindNegationToggle();
			
			this.after('*:parentChange', Y.bind(this._appendToParent, this));
			this.after([
		            'autocompletesChange', 
		            'autocompleteSourcesChange', 
		            '*:addChild', 
		            '*:removeChild'
	        	], Y.bind(this._setupAutocompletes, this));
		},

		syncUI : function() {
			this.set('negated', this.get('negated'));
			this.set('autocompletes', this.get('autocompletes'));
		},

		_renderNegationToggle: function(contentBox) {
			var translations = this.get('root').get('translations');
			
			this._negationToggleContainer = Y.Node.create('<div class="negation-toggle-container"></div>');
			var ifText = Y.Node.create('<span class="if">' + translations['if'] + '</span>');
			var ifNotText = Y.Node.create('<span class="if-not">' + translations['if-not'] + '</span>');
			this._negationToggle = Y.Node.create('<span class="negation-toggle"></span>');
			this._negationToggleContainer.append(ifText);
			this._negationToggleContainer.append(ifNotText);
			this._negationToggleContainer.append(this._negationToggle);
			contentBox.append(this._negationToggleContainer);
	    },

		_bindNegationToggle: function() {
			this._negationToggle.on('click', Y.bind(this._onNegationToggleClicked, this));
	    },

	    _renderDescription: function(contentBox) {
	    	var description = this.get('description');
			var descriptionNode = Y.Node.create('<span class="description">' + description + '</span>');
			contentBox.append(descriptionNode);
	    },
	    
	    _renderFieldContainer: function(contentBox) {
			var inner = Y.Node.create('<div class="inner"></div>');
			
			var fieldContainer = Y.Node.create(B.RuleEditor.Condition.FIELD_CONTAINER_TEMPLATE);
			fieldContainer.append(inner);

			contentBox.append(fieldContainer);
			
			this.set('fieldContainerNode', inner);
	    },

		_onNegationToggleClicked: function(event) {
			this._negationToggleContainer.toggleClass('negated');
		},

		_setupAutocompletes: function(event) {
			var self = this;
			var autocompletes = this.get('autocompletes');
			var autocompleteSources = this.get('autocompleteSources');
			
			if (this._dependencyEvents) {
				for (var i = 0; i < this._dependencyEvents.length; ++i) {
					this._dependencyEvents[i].detach();
				}
			}
			this._dependencyEvents = [];

			var childrenByName = {};
			this.each(function (child) {
				childrenByName[child.get('fieldName')] = child;
			});

			this.each(function (child) {
				var hint = child.get('hint');
				if (!hint)
					return;
				
				var autocomplete = autocompletes[hint];
				if (!autocomplete)
					return;
				
				var dependsOn = autocomplete.dependsOn;
				if (dependsOn) {
					var fieldDependency = childrenByName[dependsOn];
					self._dependencyEvents.push(fieldDependency.on('*:fieldValueModified', function(event) {
						var fieldValue = event.field.get('fieldValue');
						var sourceName = autocomplete.source[fieldValue];
						if (sourceName) {
							child.set('autocompleteSource', autocompleteSources[sourceName]);

						} else {
							child.set('autocompleteSource', []);
						}
					}));
					fieldDependency.set('fieldValue', fieldDependency.get('fieldValue'));
					
				} else {
					child.set('autocompleteSource', autocompleteSources[autocomplete.source]);
				}
			});
		}

	}, {
		// Static properties
	    
		FIELD_CONTAINER_TEMPLATE: '<div class="' + _classNames.conditionFieldContainer + '"></div>',
	    
		ATTRS : {
			description: {},
			fieldContainerNode: {},
	        negated: {
	            setter: function(value) {
	                if (value)
	                	this._negationToggleContainer.addClass('negated');
	                else 
	                	this._negationToggleContainer.removeClass('negated');
	                return value;
	            },
	            getter: function() {
	                return this._negationToggleContainer.hasClass('negated');
	            },
	            validator: Lang.isBoolean
	        },
	        autocompletes: {
	        	value: {}
	        },
	        autocompleteSources: {
	        	value: {}
	        }
		}
	});

	// GROUP
	var GroupCondition = Y.Base.create('ruleEditorGroupCondition', BaseCondition, [ Y.WidgetParent, Y.WidgetChild ], {
		// Prototype

		renderUI : function() {
			this._appendToParent();

			var contentBox = this.get('contentBox');
			this._renderButtons(contentBox);

	        var conditionBodyNode = this._renderConditionBodyNode(contentBox);
	        
			this._renderLogicOperatorSelector(conditionBodyNode);
			this._renderConditionContainer(conditionBodyNode);
		},

		bindUI : function() {
			this._bindButtons();
			
			this.after('*:parentChange', Y.bind(this._appendToParent, this));
		},

		syncUI : function() {
			this.set('logicOperator', this.get('logicOperator'));
		},

		_renderLogicOperatorSelector: function(contentBox) {
			var translations = this.get('root').get('translations');
			
			this._logicOperatorSelector = Y.Node.create('<select class="logic-operator-selector"></select>');
			this._logicOperatorSelector.append(Y.Node.create('<option value="any">' + translations['any'] + '</option>'));
			this._logicOperatorSelector.append(Y.Node.create('<option value="all">' + translations['all'] + '</option>'));
			this._logicOperatorSelector.append(Y.Node.create('<option value="not-all">' + translations['not-all'] + '</option>'));
			this._logicOperatorSelector.append(Y.Node.create('<option value="none">' + translations['none'] + '</option>'));

			var logicOperatorSelectorContainer = Y.Node.create('<div class="logic-operator-selector-container"></div>');
			logicOperatorSelectorContainer.append(this._logicOperatorSelector);

			contentBox.append(logicOperatorSelectorContainer);
		},
		
	    _renderConditionContainer: function(contentBox) {
			var inner = Y.Node.create('<div class="inner"></div>');
			
			var conditionContainer = Y.Node.create(B.RuleEditor.GroupCondition.CONDITION_CONTAINER_TEMPLATE);
			conditionContainer.append(inner);

			contentBox.append(conditionContainer);
			
			this.set('conditionContainerNode', inner);
	    },

	}, {
		// Static properties

		CONDITION_CONTAINER_TEMPLATE: '<div class="' + _classNames.ruleEditorConditionContainer + '"></div>',
	    
		ATTRS : {
			logicOperator: {
	            setter: function(value) {
	            	this._logicOperatorSelector.set('value', value);
	                return value;
	            },
	            getter: function() {
	                return this._logicOperatorSelector.get('value');
	            }
	        },
			conditionContainerNode: {}
		}
	});

	// FIELD
	var Field = Y.Base.create('ruleEditorField', Y.Widget, [ Y.WidgetChild ], {
		// Prototype

		renderUI : function() {
			this._appendToParent();
		},

		_appendToParent : function() {
			var boundingBox = this.get('boundingBox');
			var container = this.get('parent').get('fieldContainerNode');
			container.appendChild(boundingBox);
		}

	}, {
		// Static properties

		ATTRS : {
	        label: {},
			fieldName: {},
			hint: {}
		}
	});

	// TEXT FIELD
	var TextField = Y.Base.create('ruleEditorTextField', Field, [ Y.WidgetChild ], {
		// Prototype

		initializer : function() {
			this.publish('fieldValueModified', {
		        emitFacade: true
		    });
		},
		
		renderUI : function() {
			this._appendToParent();

			var contentBox = this.get('contentBox');
			this._renderTextField(contentBox);
		},
		
		bindUI : function() {
			var self = this;
			this.after('autocompleteSourceChange', Y.bind(this._onAutocompleteSourceChange, this));
			this._textField.on('valueChange', function(event) {
				self.fire('fieldValueModified', {
					field: self
				});
			});
		},

		syncUI : function() {
			this.set('fieldValue', this.get('fieldValue'));
			this.set('autocompleteSource', this.get('autocompleteSource'));
		},

		_renderTextField : function(contentBox) {
			this._textField = Y.Node.create('<input type="text" />');
			var label = Y.Node.create('<label><span>' + this.get('label') + '</span></label>');
			label.append(this._textField);
			contentBox.append(label);
		},

		_onAutocompleteSourceChange : function(contentBox) {
			var autocompleteSource = this.get('autocompleteSource');
			if (autocompleteSource != null && autocompleteSource.length > 0) {
				if (this._textField.ac != null) {
					this._textField.ac.set('source', autocompleteSource);

				} else {
					this._textField.plug(Y.Plugin.AutoComplete, {
						resultHighlighter: 'phraseMatch',
						resultFilters: ['charMatch'],
						maxResults: 20,
						minQueryLength:0,
						source: autocompleteSource
					});
				}

			} else if (this._textField.ac != null) {
				this._textField.unplug(Y.Plugin.Autocomplete);
			}
		}

	}, {
		// Static properties

		ATTRS : {
	        fieldValue: {
            	setter: function(value) {
        	    	this._textField.set('value', value);
    				this.fire('fieldValueModified', {
    					field: this
    				});
        	    	return value;
        	    },
        	    getter: function() {
        	    	return this._textField.get('value');
        	    }
	        },
	        autocompleteSource: {
	        	value:[]
	        }
		}
	});
	
	// CHECKBOX
	var Checkbox = Y.Base.create('ruleEditorCheckbox', Field, [ Y.WidgetChild ], {
		// Prototype

		renderUI : function() {
			this._appendToParent();

			var contentBox = this.get('contentBox');
			this._renderCheckbox(contentBox);
		},

		syncUI : function() {
			this.set('fieldValue', this.get('fieldValue'));
		},

		_renderCheckbox : function(contentBox) {
			this._checkbox = Y.Node.create('<input type="checkbox" />');
			var label = Y.Node.create('<label><span>' + this.get('label') + '</span></label>');
			label.append(this._checkbox);
			contentBox.append(label);
		}

	}, {
		// Static properties

		ATTRS : {
	        fieldValue: {
            	setter: function(checked) {
        	    	this._checkbox.set('checked', checked);
        	    	return checked;
        	    },
        	    getter: function() {
        	    	return this._checkbox.get('checked');
        	    },
	            validator: Lang.isBoolean
	        }
		}
	});

	B.RuleEditor = RuleEditor;
	B.RuleEditor.Condition = Condition;
	B.RuleEditor.GroupCondition = GroupCondition;
	B.RuleEditor.TextField = TextField;
	B.RuleEditor.Checkbox = Checkbox;

}, '', {
	requires : [ 'widget', 'widget-parent', 'widget-child', 'classnamemanager', 'event-custom', 'dropdown', 'autocomplete', 'autocomplete-highlighters', 'autocomplete-filters', 'event-valuechange' ]
});
