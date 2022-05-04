classdef LinkedNode < handle % for identify the Glopal & Countries & states
    properties %(Access = {?DList ?LinkedNode})
        Owner
        Name
        Next
        Prev
        Cases
        Death
    end
    methods
        function a = LinkedNode(name) % a is nodes
            if nargin ==1
                a.Name = char(name);
            end
            if name == ""
                a.Name = 'All';
            end
        end
    end
end