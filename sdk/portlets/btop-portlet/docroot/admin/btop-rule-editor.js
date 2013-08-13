
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

YUI.add('btop-rule-editor', function(Y) {

	Y.namespace('Beorn');
	var B = Y.Beorn;

	// EDITOR
	var BtopRuleEditor = Y.Base.create('btopRuleEditor', B.RuleEditor, [ Y.WidgetParent ], {
		// Prototype

		bindUI : function() {
			this._bindEditorUI();
			this._bindBtopEditorUI();
		},

		syncUI : function() {
			this.set('conditionsDeclaration', this.get('conditionsDeclaration'));
		},
		
		createCondition : function(config) {
			return new B.BtopRuleEditor.BtopCondition(config);
		},
		
		createGroupCondition : function(config) {
			return new B.BtopRuleEditor.BtopGroupCondition(config);
		},

		importCondition: function(condition) {
			this._importCondition(this, condition, false);
		},

		negateCondition: function(condition) {
			var negationBuilder = this.get('negationBuilder');
			return negationBuilder(condition);
		},
		
		getCondition: function() {
			var self = this;

			var condition = null;
			this.each(function (child) {
				condition = child.exportCondition(self);
			});
			return condition;
		},

		_bindBtopEditorUI : function() {
			this.on('conditionsDeclarationChange', Y.bind(this._onConditionsDeclarationChange, this));
		},

		_onConditionsDeclarationChange : function() {
			this._generateConditionBuilders();
		},
		
		_generateConditionBuilders: function() {
			var self = this;
			var conditionsDeclaration = this.get('conditionsDeclaration');
			var conditionListType = this.get('conditionListType');
			var conditionType = this.get('conditionType');
			var fieldMapping = this.get('fieldMapping');
			var fieldTypeToOperator = this.get('fieldTypeToOperator');
			var translations = this.get('translations');
			var autocompletes = this.get('autocompletes');
			var autocompleteSources = this.get('autocompleteSources');

			var conditionBuilders = {};
	    	for (var conditionKey in conditionsDeclaration) {
	    		(function(conditionKey) {
		    		var conditionDeclaration = conditionsDeclaration[conditionKey];
		    		
					var isGroup = false;
					var isModifier = false;
					
					var declarationFields = conditionDeclaration.fields;
					for (var i = 0; i < declarationFields.length; ++i) {
						var field = declarationFields[i];
						
						if (field.type === conditionListType)
							isGroup = true;
							
						if (field.type === conditionType)
							isModifier = true;
					}
	
					if (isGroup || isModifier)
						return;

		    		var conditionName = translations[conditionKey + '.name'];
		    		
		    		conditionBuilders[conditionKey] = {
		    			name: conditionName,
		    			build: function() {
		    				if (isGroup) {
		    					var groupConditionWidget = self.createGroupCondition({
		    						logicOperator: fieldTypeToOperator[conditionKey]
		    					});
		    					
			    				return groupConditionWidget;
	
		    				} else {
			    				var conditionWidget = self.createCondition({
			    					description: conditionName,
			    					conditionClassName: conditionKey,
			    					negated: false,
			    					autocompletes: autocompletes,
			    					autocompleteSources: autocompleteSources
			    				});
			    				var declarationFields = conditionDeclaration.fields;
			    				for (var i = 0; i < declarationFields.length; ++i) {
			    					var field = declarationFields[i];
			    					var fieldWidget = new fieldMapping[field.type]({
			    						label: field.label,
			    						fieldName: field.name,
			    						hint: field.hint
			    					});
			    					conditionWidget.add(fieldWidget);
			    				}
		
			    				return conditionWidget;
		    				}
		    			}
		    		};

	    		}(conditionKey));
	    	}
			
			this.set('conditionBuilders', conditionBuilders);
		},
		
		_importCondition: function(parent, condition, negated) {
			var conditionsDeclaration = this.get('conditionsDeclaration');
			var conditionClassNameKey = this.get('conditionClassNameKey');
			var conditionListType = this.get('conditionListType');
			var conditionType = this.get('conditionType');
			var fieldMapping = this.get('fieldMapping');
			var fieldTypeToOperator = this.get('fieldTypeToOperator');
			var negatedFieldTypeToOperator = this.get('negatedFieldTypeToOperator');
			var translations = this.get('translations');
			var autocompletes = this.get('autocompletes');
			var autocompleteSources = this.get('autocompleteSources');

			var className = condition[conditionClassNameKey];
			var declaration = conditionsDeclaration[className];

			if (declaration == null)
				return;
			
			var isGroup = false;
			var isModifier = false;
			
			var declarationFields = declaration.fields;
			for (var i = 0; i < declarationFields.length; ++i) {
				var field = declarationFields[i];
				
				if (field.type === conditionListType)
					isGroup = true;
					
				if (field.type === conditionType)
					isModifier = true;
			}

    		var conditionName = translations[className + '.name'];
			
			if (isModifier) {
				var field = declarationFields[0];
				var subCondition = condition[field.name];
				this._importCondition(parent, subCondition, !negated);

			} else if (isGroup) {
				var field = declarationFields[0];
				var subConditions = condition[field.name];
				var groupConditionWidget = this.createGroupCondition({
					logicOperator: negated ? negatedFieldTypeToOperator[className] : fieldTypeToOperator[className]
				});
				
				for (var i = 0; i < subConditions.length; ++i) {
					var subCondition = subConditions[i];
					this._importCondition(groupConditionWidget, subCondition, false);
				}
				parent.add(groupConditionWidget);

			} else {
				var conditionWidget = this.createCondition({
					description: conditionName,
					conditionClassName: className,
					negated: negated,
					autocompletes: autocompletes,
					autocompleteSources: autocompleteSources
				});
				for (var i = 0; i < declarationFields.length; ++i) {
					var field = declarationFields[i];
					var fieldWidget = new fieldMapping[field.type]({
						label: field.label,
						fieldName: field.name,
						fieldValue: condition[field.name],
						hint: field.hint
					});
					conditionWidget.add(fieldWidget);
				}
				
				parent.add(conditionWidget);
			}
		}

	}, {
		// Static properties

		ATTRS : {
			conditionsDeclaration: {
				value: {}
			},
			conditionClassNameKey: {},
			conditionListType: {},
			conditionType: {},
			fieldMapping: {},
			fieldTypeToOperator: {},
			operatorToFieldType: {},
			negatedFieldTypeToOperator: {},
			negationBuilder: {},
			autocompletes : {
				value: {}
			},
			autocompleteSources: {
				value: {}
			}
		}
	});

	// CONDITION
	var BtopCondition = Y.Base.create('btopRuleEditorCondition', B.RuleEditor.Condition, [ Y.WidgetParent, Y.WidgetChild ], {
		// Prototype

		exportCondition: function(editor) {
			var condition = {};
			condition[editor.get('conditionClassNameKey')] = this.get('conditionClassName');
			this.each(function (child) {
				condition[child.get('fieldName')] = child.get('fieldValue');
			});
			if (this.get('negated'))
				condition = editor.negateCondition(condition);
			return condition;
		}

	}, {
		// Static properties

		ATTRS : {
			conditionClassName: {}
		}
	});

	// GROUP
	var BtopGroupCondition = Y.Base.create('btopRuleEditorGroupCondition', B.RuleEditor.GroupCondition, [ Y.WidgetParent, Y.WidgetChild ], {
		// Prototype

		exportCondition: function(editor) {
			var operatorToFieldType = editor.get('operatorToFieldType');
			var fieldType =  operatorToFieldType[this.get('logicOperator')];
			
			var condition = {conditions: []};
			condition[editor.get('conditionClassNameKey')] = fieldType.type;
			this.each(function (child) {
				condition.conditions.push(child.exportCondition(editor));
			});
			if (fieldType.negated)
				condition = editor.negateCondition(condition);
			return condition;
		}

	}, {
		// Static properties

		ATTRS : {
		}
	});
	
	B.BtopRuleEditor = BtopRuleEditor;
	B.BtopRuleEditor.BtopCondition = BtopCondition;
	B.BtopRuleEditor.BtopGroupCondition = BtopGroupCondition;

}, '', {
	requires : [ 'rule-editor' ]
});
