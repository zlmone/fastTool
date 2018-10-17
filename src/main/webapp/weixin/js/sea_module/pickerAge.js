define(function(require, exports, module) {
	return {
		name: 'picker-age',
		data: function() {
			return {
				age: ''
			}
		},
		props: ['label', 'value', 'placeholder', 'unit', 'type'],
		computed: {
			getAge: function() {
				if (this.type === 'age' || !this.type) {
					return this.age ? (this.unit ? this.age + this.unit : this.age) : this.age
				} else {
					return this.age
				}
			},
			startDate: function() {
				return new Date('1930/01/01');
			},
			endDate: function() {
				return new Date();
			}
		},
		methods: {
			onTouch: function(){
				return false
			},
			onSelectOpen: function() {
				this.$refs.pickerAge.open();
			},
			onSelectChange: function(value) {
				if (typeof value === "string") value = new Date(value);
				var now = new Date(),
					nowYeas = now.getFullYear(),
					nowMonth = now.getMonth(),
					nowDate = now.getDate(),
					meYeas = value.getFullYear(),
					meMonth = value.getMonth(),
					meDate = value.getDate(),
					age = nowYeas - meYeas;
				if ((nowMonth < meMonth) || (nowMonth === meMonth && nowDate < meDate)) age -= 1;
				this.age = this.type === 'age' || !this.type ? age : meYeas + '-' + (meMonth + 1) + '-' + meDate;
				this.$emit('input', meYeas + '/' + (meMonth + 1) + '/' + meDate);
			}
		},
		created: function() {
			this.value && this.onSelectChange(this.value);
		},
		template: `
			<div v-on:touchmove.prevent="onTouch">
				<div class="picker-select" v-on:click="onSelectOpen">
					<mt-field type="text" v-bind:label="label" v-bind:value="getAge" readonly="true" disableClear="true" v-bind:placeholder="placeholder"></mt-field>
				</div>
				<mt-datetime-picker type="date" ref="pickerAge" v-bind:value="value" v-on:confirm="onSelectChange" v-bind:start-date="startDate" v-bind:end-date="endDate" year-format="{value} 年" month-format="{value} 月" date-format="{value} 日"></mt-datetime-picker>
			</div>
		`
	}
});