import React from "react";



interface DepartmentTabsProps {

  departments: string[];

  activeTab: string;

  onTabChange: (department: string) => void;

}



const DepartmentTabs: React.FC<DepartmentTabsProps> = ({

  departments,

  activeTab,

  onTabChange,

}) => {

  return (

    <div className="department-tabs">

      <button

        className={activeTab === "All" ? "active" : ""}

        onClick={() => onTabChange("All")}

      >

        All

      </button>

      {departments.map((department) => (

        <button

          key={department}

          className={activeTab === department ? "active" : ""}

          onClick={() => onTabChange(department)}

        >

          {department}

        </button>

      ))}

    </div>

  );

};



export default DepartmentTabs;
