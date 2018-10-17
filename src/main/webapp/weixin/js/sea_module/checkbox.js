define(function(require, exports, module) {
	return {
		name: 'checkbox-btn',
		data: function() {
			return {
				checked: false
			}
		},
		props: ['title', 'value'],
		computed: {
			isChecked: function() {
				return typeof this.value === "boolean" ? this.value : true
			}
		},
		methods: {
			onInput: function(e) {
				this.$emit('input', e.target.checked);
			}
		},
		created: function() {
			this.checked = this.value ? true : false;
		},
		template: `
			<label class="checkbox-btn">
				<input type="checkbox" v-bind:checked="checked" v-on:input="onInput">
				<i></i>
				<span>{{title}}</span>
			</label>
		`
	}
});