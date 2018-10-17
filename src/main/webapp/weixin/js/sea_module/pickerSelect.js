define(function(require, exports, module) {
	return {
		name: 'picker-select',
		data: function() {
			return {
				visible: false,
				inValue: ''
			}
		},
		props: ['label', 'value', 'options'],
		computed: {
			getValue: function() {
				return typeof this.value === 'string' ? this.value : this.value.join('-')
			}
		},
		methods: {
			onSelectOpen: function() {
				this.visible = true;
			},
			onSelectClose: function(hasSubmit) {
				this.visible = false;
				hasSubmit && this.$emit('input', this.inValue.toString());
			},
			onSelectChange: function(picker, values) {
				if (this.inValue.length) {
					this.inValue = values;
				} else {
					this.value.length && setTimeout(function() {
						picker.setValues(typeof this.value === 'string' ? [this.value] : this.value);
					}.bind(this), 100);
					this.inValue = values;
				}
			}
		},
		template: `
			<div>
				<div class="picker-select" v-on:click="onSelectOpen">
					<mt-field type="text" v-bind:label="label" v-bind:value="getValue" readonly="true" disableClear="true" placeholder="请选择"></mt-field>
				</div>
				<mt-popup v-model="visible" position="bottom">
					<mt-header>
						<mt-button slot="left" v-on:click="onSelectClose(false)">取消</mt-button>
						<mt-button slot="right" v-on:click="onSelectClose(true)">确定</mt-button>
					</mt-header>
					<mt-picker value-key="value" visible-item-count="7" v-bind:slots="options" v-on:change="onSelectChange"></mt-picker>
				</mt-popup>
			</div>
		`
	}
});