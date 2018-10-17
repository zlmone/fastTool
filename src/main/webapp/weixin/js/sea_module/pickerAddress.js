define(function(require, exports, module) {
	var level = 3;
	var info = '请选择';
	var cityData = require('cityData');
	var addHint = function(arr) {
		var newArr = [{
			text: info,
			length: arr ? arr.length : 0
		}];
		return arr ? newArr.concat(arr) : newArr
	};
	return {
		name: 'picker-address',
		data: function() {
			return {
				visible: false,
				addressLength: 3,
				address: []
			}
		},
		props: ['label', 'value', 'accuracy'],
		computed: {
			getOptions: function() {
				var options = [];
				for (var i = 0; i < this.addressLength; i++) {
					options.push({
						flex: 1,
						values: i === 0 ? addHint(cityData) : addHint(),
						className: 'slot',
						textAlign: 'center'
					})
				}
				return options
			}
		},
		methods: {
			onSelectOpen: function() {
				this.visible = true;
			},
			onSelectClose: function(hasSubmit) {
				if (hasSubmit) {
					for (var i = 0; i < this.address.length; i++) {
						if (this.address[i].text === info && this.address[i].length > 0) {
							this.$toast(info + '!');
							return
						}
					}
					hasSubmit && this.$emit('input', this.address.map(function(oj) {
						return oj.text
					}).join(' '));
				}
				this.visible = false;
			},
			onSelectChange: function(picker, values) {
				for (var i = 1, index = 0; i < values.length; i++, index++) {
					if (values[index].children) {
						values[i].length = values[index].children.length;
						picker.setSlotValues(i, addHint(values[index].children));
					}
					for (var j = i + 1; j < values.length; j++) picker.setSlotValues(j, addHint());
				}
				this.address = values;
			}
		},
		beforeMount: function() {
			var accuracy = parseInt(this.accuracy);
			this.addressLength = !isNaN(accuracy) && this.accuracy >= 1 && this.accuracy <= 3 ? this.accuracy : this.addressLength;
		},
		template: `
			<div>
				<div class="picker-select" v-on:click="onSelectOpen">
					<mt-field type="text" v-bind:label="label" v-bind:value="value" readonly="true" disableClear="true" placeholder="` + info + `"></mt-field>
				</div>
				<mt-popup v-model="visible" position="bottom">
					<mt-header>
						<mt-button slot="left" v-on:click="onSelectClose(false)">取消</mt-button>
						<mt-button slot="right" v-on:click="onSelectClose(true)">确定</mt-button>
					</mt-header>
					<mt-picker value-key="text" visible-item-count="7" v-bind:slots="getOptions" v-on:change="onSelectChange"></mt-picker>
				</mt-popup>
			</div>
		`
	}
});