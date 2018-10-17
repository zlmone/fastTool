define(function(require, exports, module) {
	var isArray = function(arr) {
		return Object.prototype.toString.call(arr) === '[object Array]';
	};
	var inputType = {
		number: {
			input: function(val, maxlength) {
				var valArr = val.match(/([0-9])/g);
				return isArray(valArr) && val.length ? valArr.slice(0, maxlength).join('') : ''
			},
			blur: function(val, maxlength) {
				return val
			}
		},
		card: {
			input: function(val, maxlength) {
				var valArr = val.match(/([0-9])/g);
				val = isArray(valArr) && val.length ? valArr.slice(0, maxlength).join('') : '';
				valArr = val.match(/([0-9]{1,4})/g);
				return isArray(valArr) && val.length ? valArr.join('  ') : '';
			},
			blur: function(val, maxlength) {
				return val
			}
		},
		idNumber: {
			input: function(val, maxlength) {
				var valArr = val.toUpperCase().match(/^[1-9][0-9]{0,16}[0-9Xx]?/);
				return isArray(valArr) && val.length ? valArr[0].substr(0, maxlength) : ''
			},
			blur: function(val, maxlength) {
				return val
			}
		},
		name: {
			input: function(val, maxlength) {
				var valArr = val.match(/([\u4e00-\u9fa5_a-zA-Z])/g);
				return isArray(valArr) && val.length ? valArr.slice(0, maxlength).join('') : ''
			},
			blur: function(val, maxlength) {
				return val
			}
		},
		money: {
			input: function(val, maxlength) {
				var valArr = val.match(/([0-9]+)(\.[0-9]{0,2})?/);
				return isArray(valArr) && val.length ? valArr[0].substr(0, maxlength) : ''
			},
			blur: function(val, maxlength) {
				var num = parseFloat(val.replace(/\.$/, ''));
				return isNaN(num) ? 0 : num
			}
		},
		phone: {
			input: function(val, maxlength) {
				var valArr = val.match(/(1[0-9]{0,10})/);
				return isArray(valArr) && val.length ? valArr[0].substr(0, maxlength) : ''
			},
			blur: function(val, maxlength) {
				return val
			}
		},
		email: {
			input: function(val, maxlength) {
				var valArr = val.match(/([a-zA-Z0-9_.-@]+)/);
				return isArray(valArr) && val.length ? valArr[0].substr(0, maxlength) : ''
			},
			blur: function(val, maxlength) {
				return val
			}
		},
		text: {
			input: function(val, maxlength) {
				return val
			},
			blur: function(val, maxlength) {
				return val
			}
		}
	};
	return {
		name: 'field-input',
		data: function() {
			return {
				val: '',
				typeName: 'text'
			}
		},
		props: ['label', 'placeholder', 'value', 'type', 'maxlength', 'unit'],
		methods: {
			onInput: function(e) {
				var input = e.target;
				input.value = inputType[this.typeName].input(input.value, this.maxlength ? this.maxlength : 99999);
				this.val = input.value;
				this.$emit('input', this.val.toString());
			},
			onBlur: function(e) {
				var input = e.target;
				input.value = inputType[this.typeName].blur(input.value, this.maxlength ? this.maxlength : 99999);
				this.val = input.value;
				this.$emit('input', this.val.toString());
				this.$emit('blur', e);
			}
		},
		created: function() {
			this.val = this.value.toString();
			this.typeName = this.type ? this.type : 'text';
		},
		template: `
			<a class="mint-cell mint-field">
				<div class="mint-cell-wrapper">
					<div class="mint-cell-title">
						<span class="mint-cell-text">{{label}}</span>
					</div>
					<div class="mint-cell-value">
						<input type="text" class="mint-field-core" v-bind:placeholder="placeholder" v-bind:value="val" v-on:input="onInput" v-on:blur="onBlur">
						<span class="mint-field-unit" v-if="unit">{{unit}}</span>
					</div>
				</div>
				<slot></slot>
			</a>
		`
	}
});