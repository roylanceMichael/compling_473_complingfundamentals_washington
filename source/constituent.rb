class Constituent
	attr_accessor :pos, :value, :parent, :children

	def initialize
		@children = []
	end

	def addChild(child)
		@children.push(child)
	end

	def has_value?
		return @value != nil
	end


	def has_children?
		return @value.length > 0
	end

	def to_s
		childrenStr = ""

		if @children.length > 0
			childrenStr = "["
			@children.each do |child|
				childrenStr = "#{childrenStr}#{child}, "
			end
			childrenStr = "#{childrenStr.strip}]"
		end

		"#{@pos} #{value}#{childrenStr}"
	end
end