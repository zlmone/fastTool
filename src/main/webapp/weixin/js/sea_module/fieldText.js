define(function(require, exports, module) {
	return {
		name: 'field-text',
		props: ['label', 'title', 'value', 'icon', 'href'],
		template: `
			<a class="mint-cell mint-field field-text" v-bind:href="href">
				<div class="mint-cell-wrapper">
					<i class="mint-cell-icon" v-if="icon" v-bind:class="icon"></i>
					<div class="mint-cell-title">
						<span class="mint-cell-text">{{label}}</span>
					</div>
					<div class="mint-cell-value">
						<span v-if="title" class="mint-field-core title">{{title}}</span>
						<span class="mint-field-core">{{value}}</span>
					</div>
				</div>
			</a>
		`
	}
});