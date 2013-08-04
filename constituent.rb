class Constituent
	attr_accessor :pos, :value, :parent

	def has_value?
		return @value != nil
	end


	def has_children?
		return @value != nil && @value == Array
	end

	def to_s
		"#{@pos} #{value}"
	end
end